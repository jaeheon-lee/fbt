<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <!-- 매치 생성 Label -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>매치 수정</span>
        </v-col>
      </v-row>
      <!-- 매치 생성 본문 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
          <v-row justify="center" class="my-5">
            경기 일정 수정을 원하시면 일정 페이지에서 진행해주세요.
          </v-row>
          <!-- 매치 부분 -->
          <div>
            <!-- 대기가능시간, 최소인원, 마감시간 -->
            <!-- 대기가능시간, 최소인원, 마감시간 Label-->
            <v-row class="mx-0 px-0">
              <v-col cols="4" class="text-left mx-0 px-0 pb-2"
                >대기 가능 시간</v-col
              >
              <v-col cols="4" class="text-left mx-0 px-0 pb-2"
                >상대편 최소 인원</v-col
              >
              <v-col cols="4" class="text-left mx-0 px-0 pb-2">마감 시간</v-col>
            </v-row>
            <!-- 대기가능시간, 최소인원, 마감시간 Input -->
            <v-row class="mx-0 px-0">
              <!-- 대기가능시간 input -->
              <v-col cols="4" class="text-left pa-0">
                <v-row
                  class="ma-0 pa-3"
                  justify="center"
                  style="border:2px solid #AD1457;border-radius:25px;"
                >
                  <v-col offset="2" cols="5" class="ma-0 pa-0">
                    <input
                      type="text"
                      style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                      v-model="search.waitingTime"
                    />
                  </v-col>
                  <v-col cols="3" class="ma-0 pa-0 text-center">
                    시간
                  </v-col>
                </v-row>
              </v-col>
              <!-- 최소인원 input -->
              <v-col cols="4" class="text-left pa-0">
                <v-row
                  class="ma-0 pa-3"
                  justify="center"
                  style="border:2px solid #AD1457;border-radius:25px;"
                >
                  <v-col offset="2" cols="5" class="ma-0 pa-0">
                    <input
                      type="text"
                      style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                      v-model="search.minNumber"
                    />
                  </v-col>
                  <v-col cols="3" class="ma-0 pa-0 text-center">
                    명
                  </v-col>
                </v-row>
              </v-col>
              <!-- 마감시간 input -->
              <v-col cols="4" class="text-left pa-0">
                <v-row
                  class="ma-0 pa-3"
                  justify="center"
                  style="border:2px solid #AD1457;border-radius:25px;"
                >
                  <input
                    type="datetime-local"
                    style="display:inline-block;width:100%;text-align:center;color:#ffffff"
                    v-model="targetDueDate"
                  />
                </v-row>
              </v-col>
            </v-row>
            <!-- 대기가능시간, 최소인원, 마감시간 끝 -->
            <!--매치글 내용-->
            <!--매치글 내용 Label-->
            <v-row class="mx-0 px-0">
              <v-col cols="12" class="text-left mx-0 px-0 pb-2"
                >매치글 내용</v-col
              >
            </v-row>
            <!--매치글 내용 Input-->
            <v-row class="mx-0 px-0">
              <v-col cols="12" class="text-left pa-0">
                <v-row
                  class="ma-0 pa-3"
                  justify="center"
                  style="border:2px solid #AD1457;border-radius:25px;"
                >
                  <textarea
                    v-model="search.content"
                    cols="10000"
                    placeholder="매치글에 남기고 싶은 말을 입력해주세요"
                    style="color:#ffffff"
                  ></textarea>
                </v-row>
              </v-col>
            </v-row>
            <!--내용 끝 -->
          </div>
          <!-- 매치 부분 끝 -->
          <!-- 제출 버튼 -->
          <v-row class="mx-0 px-0 pt-3 justify-center">
            <v-btn elevation="3" @click="updateSearch">매치등록하기</v-btn>
          </v-row>
          <!-- 제출 버튼 끝 -->
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/search/SearchUpdate.js"></script>
<style scoped src="@/assets/css/common/Insert.css"></style>
