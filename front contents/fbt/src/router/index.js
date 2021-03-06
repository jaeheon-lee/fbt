import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: () => import(/* webpackChunkName: "home" */ "../views/Home.vue")
  },
  {
    path: "/voteMatchManager",
    name: "voteMatchManager",

    component: () =>
      import(
        /* webpackChunkName: "voteManager" */ "../views/VoteMatchManager.vue"
      )
  },
  {
    path: "/voteMatch",
    name: "voteMatch",
    props: true,

    component: () =>
      import(/* webpackChunkName: "vote" */ "../views/VoteMatch.vue")
  },
  {
    path: "/loginSignin",
    name: "loginSignin",
    props: true,
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/LoginSignin.vue")
  },
  {
    path: "/team",
    name: "team",
    component: () => import(/* webpackChunkName: "team" */ "../views/Team.vue")
  },
  {
    path: "/allTeamMemberMenu",
    name: "allTeamMemberMenu",
    component: () =>
      import(
        /* webpackChunkName: "allTeamMemberMenu" */ "../views/AllTeamMemberMenu.vue"
      )
  },
  {
    path: "/privateMenu",
    name: "privateMenu",
    component: () =>
      import(/* webpackChunkName: "privateMenu" */ "../views/PrivateMenu.vue")
  },
  {
    path: "/schedule",
    name: "schedule",
    component: () =>
      import(/* webpackChunkName: "privateMenu" */ "../views/Schedule.vue")
  },
  {
    path: "/scheduleManager",
    name: "scheduleManager",
    component: () => import("../views/ScheduleManager.vue")
  },
  {
    path: "/search",
    name: "search",
    component: () => import("../views/Search.vue"),
    props: true
  },
  {
    path: "/voteMatchInsert",
    name: "voteMatchInsert",
    component: () => import("@/components/VoteMatch/VoteMatchInsert.vue"),
    props: true
  },
  {
    path: "/assign",
    name: "assign",
    component: () => import("@/views/Assign.vue"),
    props: true
  },
  {
    path: "/employManager",
    name: "employManager",
    component: () => import("@/views/EmployManager.vue"),
    props: true
  },
  {
    path: "/employ",
    name: "employ",
    component: () => import("@/views/Employ.vue"),
    props: true
  },
  {
    path: "/signin",
    name: "signin",
    component: () => import("@/views/Signin.vue"),
    props: true
  },
  {
    path: "/naver",
    name: "callback-naver",
    component: () => import("@/views/callback/Naver.vue"),
    props: true
  },
  {
    path: "/scheduleUser",
    name: "scheduleUser",
    component: () => import("@/views/ScheduleUser.vue"),
    props: true
  },
  {
    path: "/teamManage",
    name: "teamManage",
    component: () => import("@/views/TeamManage.vue"),
    props: true
  },
  {
    path: "/voteMatchUpdate",
    name: "voteMatchUpdate",
    component: () => import("@/views/update/VoteMatchUpdate.vue"),
    props: true
  },
  {
    path: "/searchUpdate",
    name: "searchUpdate",
    component: () => import("@/views/update/SearchUpdate.vue"),
    props: true
  },
  {
    path: "/assignUpdate",
    name: "assignUpdate",
    component: () => import("@/views/update/AssignUpdate.vue"),
    props: true
  },
  {
    path: "/employUpdate",
    name: "employUpdate",
    component: () => import("@/views/update/EmployUpdate.vue"),
    props: true
  },
  {
    path: "/teamInsert",
    name: "teamInsert",
    component: () => import("@/views/TeamInsert.vue")
  },
  {
    path: "/searchTeam",
    name: "searchTeam",
    component: () => import("@/views/SearchTeam.vue")
  },
  {
    path: "/teamHome",
    name: "teamHome",
    component: () => import("@/views/TeamHome.vue")
  },
  {
    path: "/myPage",
    name: "myPage",
    component: () => import("@/views/MyPage.vue")
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/Notice.vue")
  },
  {
    path: "/scheduleUpdate",
    name: "scheduleUpdate",
    component: () => import("@/views/update/ScheduleUpdate.vue"),
    props: true
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
