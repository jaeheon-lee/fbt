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

    component: () =>
      import(/* webpackChunkName: "vote" */ "../views/VoteMatch.vue")
  },
  {
    path: "/",
    name: "login",
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/Login.vue")
  },
  {
    path: "/team",
    name: "team",
    component: () => import(/* webpackChunkName: "team" */ "../views/Team.vue")
  },
  {
    path: "/managerMenu",
    name: "managerMenu",
    component: () =>
      import(/* webpackChunkName: "managerMenu" */ "../views/ManagerMenu.vue")
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
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
