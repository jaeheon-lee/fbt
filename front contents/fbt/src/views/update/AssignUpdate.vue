<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <!-- 양도 수정 Label -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>양도 수정</span>
        </v-col>
      </v-row>
      <!-- 양도 수정 본문 -->
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-divider color="white"></v-divider>
          <v-row justify="center" class="my-5">
            경기 일정 수정을 원하시면 일정 페이지에서 진행해주세요.
          </v-row>
          <!-- 양도 부분 -->
          <div>
            <!-- 마감시간 -->
            <!--  마감시간 Label-->
            <v-row class="mx-0 px-0">
              <v-col cols="4" class="text-left mx-0 px-0 pb-2">마감 시간</v-col>
            </v-row>
            <!-- 마감시간 Input -->
            <v-row class="mx-0 px-0">
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
            <!-- 마감시간 끝 -->
            <!--양도글 내용-->
            <!-- 양도글  내용 Label-->
            <v-row class="mx-0 px-0">
              <v-col cols="12" class="text-left mx-0 px-0 pb-2"
                >양도글 내용</v-col
              >
            </v-row>
            <!--일정 내용 Input-->
            <v-row class="mx-0 px-0">
              <v-col cols="12" class="text-left pa-0">
                <v-row
                  class="ma-0 pa-3"
                  justify="center"
                  style="border:2px solid #AD1457;border-radius:25px;"
                >
                  <textarea
                    v-model="assignment.content"
                    cols="10000"
                    placeholder="양도글에 남기고 싶은 말을 입력해주세요"
                    style="color:#ffffff"
                  ></textarea>
                </v-row>
              </v-col>
            </v-row>
            <!--내용 끝 -->
          </div>
          <!-- 양도 부분 끝 -->
          <!-- 제출 버튼 -->
          <v-row class="mx-0 px-0 pt-3 justify-center">
            <v-btn elevation="3" @click="updateAssign">양도수정하기</v-btn>
          </v-row>
          <!-- 제출 버튼 끝 -->
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script scoped src="@/assets/js/assign/AssignUpdate.js"></script>
<style scoped src="@/assets/css/common/Insert.css"></style>
