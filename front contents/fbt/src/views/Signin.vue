<template>
  <v-container>
    <v-row justify="center" class="py-0 my-0 px-0 mx-5">
      <v-col xl="6" lg="8" cols="12" class="py-0 my-0 px-0 mx-0">
        <!-- 회원가입 라벨 -->
        <v-row class="py-0 my-5 px-0 mx-5">
          <v-col cols="12" class="pa-0 ma-0 mb-2">
            <span>회원가입</span>
            <v-divider color="white"></v-divider>
          </v-col>
        </v-row>
        <!-- 회원가입 라벨 끝 -->
        <!-- 회원가입 칸 -->
        <form>
          <v-row>
            <v-col
              offset-lg="2"
              lg="8"
              offset-md="2"
              md="8"
              offset-sm="2"
              sm="8"
              xs="12"
            >
              <!-- page1 -->
              <v-card class="mx-5 py-4" v-if="page == 1">
                <!-- 이메일-->
                <v-row class="pt-6">
                  <v-text-field
                    class="mx-15"
                    label="이메일"
                    placeholder="이메일을 입력해주세요"
                    dense
                    counter="50"
                    :rules="[rules.required]"
                    @keyup="checkDuple"
                    v-model="user.email"
                  ></v-text-field>
                </v-row>
                <v-row
                  class="ml-12 my-0 py-0 red--text"
                  style="font-size: 0.8em"
                  v-if="emailDuple"
                >
                  중복된 이메일입니다.
                </v-row>
                <!-- 이메일 끝 -->
                <!-- 비밀번호 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="비밀번호"
                    placeholder="비밀번호를 입력해주세요"
                    dense
                    counter="20"
                    type="Password"
                    :rules="[rules.required, rules.pass]"
                    v-model="user.pass"
                  ></v-text-field>
                </v-row>
                <!-- 비밀번호 끝 -->
                <!-- 비밀번호 확인 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="비밀번호 확인"
                    placeholder="비밀번호를 다시 입력해주세요"
                    dense
                    counter="20"
                    type="Password"
                    v-model="user.pass2"
                  ></v-text-field>
                </v-row>
                <v-row class="ml-12 my-0 py-0 red--text" style="font-size: 0.8em">
                  {{ isSame }}
                </v-row>
                <!-- 비밀번호 확인 끝 -->
                <!-- 이름 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="이름"
                    placeholder="이름을 입력해주세요."
                    dense
                    :rules="[rules.required]"
                    v-model="user.name"
                  ></v-text-field>
                </v-row>
                <!-- 이름 끝 -->
                <!-- 연락처 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="연락처"
                    placeholder="연락처을 입력해주세요."
                    dense
                    :rules="[rules.phone, rules.required]"
                    v-model="user.phoneNum"
                  ></v-text-field>
                </v-row>
                <!-- 연락처 끝 -->
                <!-- 성별 -->
                <v-row class="mt-3">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="성별을 선택해주세요"
                    outlined
                    :items="gender"
                    item-text="label"
                    item-value="value"
                    label="성별"
                    hide-details
                    v-model="user.gender"
                  ></v-select>
                </v-row>
                <!-- 성별 끝 -->
                <!-- 다음 버튼 -->
                <v-row justify="end" class="mt-6 mr-11">
                  <v-btn color="#6820A3" @click="page = 2">다음</v-btn>
                </v-row>
                <!-- 다음 버튼 끝 -->
              </v-card>
              <!-- page1 끝 -->
              <!-- page2 -->
              <v-card class="mx-5 py-4" v-if="page == 2">
                <!-- 주발-->
                <v-row class="pt-6">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="주발을 선택해주세요"
                    outlined
                    :items="mainFoot"
                    label="주발"
                    hide-details
                    v-model="user.mainFoot"
                  ></v-select>
                </v-row>
                <!-- 주발 끝 -->
                <!-- 포지션 -->
                <v-row class="mt-6">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="포지션을 선택해주세요"
                    outlined
                    :items="position"
                    label="포지션"
                    hide-details
                    v-model="user.position"
                  ></v-select>
                </v-row>
                <!-- 포지션 끝 -->
                <!-- 프로 여부 -->
                <v-row class="mt-6">
                  <v-select
                    class="mx-14 my-0"
                    dense
                    placeholder="프로출신인지 선택해주세요"
                    outlined
                    :items="wasPro"
                    item-text="label"
                    item-value="value"
                    label="포지션"
                    hide-details
                    v-model="user.wasPro"
                  ></v-select>
                </v-row>
                <!-- 프로여부 끝 -->
                <!-- 키 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="키 (cm)"
                    placeholder="키를 cm단위로 입력해주세요"
                    dense
                    v-model="user.height"
                  ></v-text-field>
                </v-row>
                <!-- 키 확인 끝 -->
                <!-- 체중 -->
                <v-row class="mt-6">
                  <v-text-field
                    class="mx-15"
                    label="체중 (kg)"
                    placeholder="체중을 입력해주세요."
                    dense
                    v-model="user.weight"
                  ></v-text-field>
                </v-row>
                <!-- 체중 끝 -->
                <!-- 생년월일 -->
                <v-row justify="center" class="mt-3">
                  <span style="width: 20%;">
                    <v-select
                      dense
                      placeholder="생년"
                      outlined
                      :items="year"
                      label="생년"
                      hide-details
                      v-model="getYear"
                    ></v-select>
                  </span>
                  <span class="mx-10" style="width: 20%;">
                    <v-select
                      dense
                      placeholder="월"
                      outlined
                      :items="month"
                      label="월"
                      hide-details
                      v-model="getMonth"
                    ></v-select>
                  </span>
                  <span style="width: 20%;">
                    <v-select
                      dense
                      placeholder="일"
                      outlined
                      :items="day"
                      label="일"
                      hide-details
                      v-model="getDay"
                    ></v-select>
                  </span>
                </v-row>
                <!-- 생년월일 끝 -->
                <!-- 주활동지역 -->
                <v-row justify="center" class="mt-6">
                  <span style="width: 35%;" class="mr-7">
                    <v-select
                      dense
                      placeholder="활동지역 시/도"
                      outlined
                      :items="area0"
                      label="활동지역 시/도"
                      hide-details
                      v-model="siDo"
                    ></v-select>
                  </span>
                  <span style="width: 35%;">
                    <v-select
                      dense
                      placeholder="활동지역 시/구/군"
                      outlined
                      :items="selectArea"
                      label="활동지역 시/구/군"
                      hide-details
                      v-model="siGun"
                    ></v-select>
                  </span>
                </v-row>
                <!-- 주활동지역 끝 -->
                <!-- 다음 버튼 -->
                <v-row justify="end" class="mt-6 mr-11">
                  <v-btn @click="page = 1">이전</v-btn>
                  <v-btn color="#6820A3" @click="submitUser">완료</v-btn>
                </v-row>
                <!-- 다음 버튼 끝 -->
              </v-card>
              <!-- page2 끝 -->
            </v-col>
          </v-row>
        </form>
        <!-- 회원가입 칸 끝 -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script scoped src="@/assets/js/signin/Signin.js"></script>

<style></style>
