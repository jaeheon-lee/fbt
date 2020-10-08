<template>
  <div>
    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-row
            justify="center"
            fluid
            align="center"
            class="py-0 my-0 px-0 mx-0"
          >
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                color="#AD1457"
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                >홈 투표중</v-btn
              >
            </v-col>
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                style="border:0px;"
                class="mx-0 px-0"
                >어웨이 투표중</v-btn
              >
            </v-col>
            <v-col cols="4" class="py-0 my-0 px-0 mx-0" justify="center">
              <v-btn
                tile
                dark
                large
                width="100%"
                class="mx-0 px-0"
                style="border:0px;"
                >투표완료</v-btn
              >
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="py-0 my-7 px-0 mx-0">
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0 mb-2">
          <span>홈팀 진행중 투표</span>
        </v-col>
      </v-row>
      <v-row fluid justify="center" class="py-0 my-0 px-0 mx-5">
        <v-col xl="6" lg="8" cols="12" class="pa-0 ma-0">
          <v-expansion-panels focusable hover color="red">
            <v-row
              class="ma-0 pa-0"
              style="background-color:#121212;font-size: 0.75rem;color:rgba(255, 255, 255, 0.9)"
            >
              <v-col cols="5" class="text-center ma-0 pa-2">투표 기간</v-col>
              <v-col cols="3" class="text-center ma-0 pa-2">경기일시</v-col>
              <v-col cols="4" class="text-center ma-0 pa-2">경기장소</v-col>
            </v-row>
            <v-expansion-panel v-for="(vote, i) in votes" :key="i">
              <v-expansion-panel-header
                hide-actions
                class="ma-0 pa-0"
                style="min-height:12px!important;background-color:#121212;font-size: 0.65rem;line-height:100%;color:rgba(255, 255, 255, 0.7)"
              >
                <v-row class="ma-0 pa-0">
                  <v-col cols="5" class="text-center"
                    >{{ vote.reg_date }} ~ {{ vote.due_date.split("-")[1] }}-{{
                      vote.due_date.split("-")[2]
                    }}</v-col
                  >

                  <v-col cols="3" class="text-center">
                    {{ vote.match_date }}
                  </v-col>
                  <v-col cols="4" class="text-center">
                    {{ vote.match_place }}
                  </v-col>
                </v-row>
              </v-expansion-panel-header>

              <v-expansion-panel-content
                style="background-color:#121212;font-size: 0.75rem;line-height:100%;color:rgba(255, 255, 255, 0.9)"
              >
                <v-row class="ma-0 pa-0">
                  <v-col cols="4" class="mx-0 px-0">투표하기</v-col>
                  <v-col cols="8" class="text-right mx-0 px-0"
                    >투표 마감일시 {{ vote.due_date }}</v-col
                  >
                </v-row>
                <v-divider></v-divider>
                <!--팀-->
                <v-row class="mx-0 px-0">
                  <v-col cols="6" class="textc-left mx-0 pl-0 pr-1 pb-2"
                    >홈팀</v-col
                  >
                  <v-col cols="6" class="text-left mx-0 pr-0 pl-1 pb-2"
                    >어웨이팀</v-col
                  >
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="6" class="pa-0 pr-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >FC답십리</v-row
                    ></v-col
                  >
                  <v-col cols="6" class="pa-0 pl-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >미정</v-row
                    ></v-col
                  >
                </v-row>
                <!--팀 끝-->
                <!--경기 일시-->
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left mx-0 px-0 pb-2"
                    >경기 일시</v-col
                  >
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left pa-0"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >2020-09-30 09:00 ~ 11:00 2시간 경기</v-row
                    ></v-col
                  >
                </v-row>
                <!-- 경기 일시 끝 -->
                <!-- 경기장 정보-->
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left mx-0 px-0 pb-2"
                    >경기장 정보</v-col
                  >
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left pa-0"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >서울시 성동구 서울숲 공원</v-row
                    ></v-col
                  >
                </v-row>
                <!--경기장 정보 끝 -->
                <!--경기타입비용주차-->
                <v-row class="mx-0 px-0">
                  <v-col cols="4" class="textc-left mx-0 pl-0 pr-1 px-0 pb-2"
                    >경기타입</v-col
                  >
                  <v-col
                    cols="4"
                    class="text-left mx-0 px-0 pb-2"
                    style="padding-left:2px;padding-right:2px;"
                    >비용</v-col
                  >
                  <v-col cols="4" class="text-left mx-0 pr-0 pl-1 px-0 pb-2"
                    >주차</v-col
                  >
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="4" class="pa-0 pr-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >11:11</v-row
                    ></v-col
                  >
                  <v-col
                    cols="4"
                    class="py-0"
                    style="padding-left:2px;padding-right:2px;"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >9만원</v-row
                    ></v-col
                  >
                  <v-col cols="4" class="pa-0 pl-1"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >주차가능</v-row
                    ></v-col
                  >
                </v-row>
                <!--경기타입비용주차-->
                <!--내용-->
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left mx-0 px-0 pb-2">내용</v-col>
                </v-row>
                <v-row class="mx-0 px-0">
                  <v-col cols="12" class="text-left pa-0"
                    ><v-row
                      class="ma-0 pa-3"
                      justify="center"
                      style="border:2px solid #AD1457;border-radius:25px;"
                      >늦지 마세요.</v-row
                    ></v-col
                  >
                </v-row>
                <!--내용 끝 -->
                <!--투표내용-->
                <v-row class="justify-space-between ma-0 pa-0 mt-4">
                  <div>총인원: 30명</div>
                  <div>투표인원:15명</div>
                  <div>불참: 8명</div>
                  <div>참여: 7명</div>
                </v-row>
                <v-row justify="end" class="ma-0 pa-0 my-3">
                  <div>지인: 1명</div>
                </v-row>
                <v-divider></v-divider>
                <v-row justify="end" class="ma-0 pa-0 my-3">
                  <div>경기참석 인원: 8명</div>
                </v-row>
                <!--투표내용 끝-->
                <!--투표버튼-->
                <v-row class="justify-center ma-0 pa-0 mt-4">
                  <v-btn small width="35px" color="#AD1457" class="mr-1"
                    >참 석</v-btn
                  >
                  <v-btn
                    small
                    width="35px"
                    color="rgba(235, 255, 0,0.7)"
                    class="ml-1"
                    >불참석</v-btn
                  >
                </v-row>
                <!--투표버튼 끝-->
                <v-row class="justify-end mt-5">
                  <div>지인 참석이 가능한 경기입니다.</div>
                </v-row>
                <v-row class="justify-end mt-1">
                  <div style="color:rgba(235, 255, 0,0.7)">지인 초청하기</div>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      votes: [
        {
          reg_date: "2020-09-18 09:00",
          due_date: "2020-09-22 09:00",
          match_date: "2020-09-30 09:00",
          match_place: "서울시 성동구 서울숲 공원"
        },
        {
          reg_date: "2020-09-19 09:00",
          due_date: "2020-09-30 09:00",
          match_date: "2020-10-06 09:00",
          match_place: "서울시 성동구 서울숲 공원"
        }
      ]
    };
  }
};
</script>
