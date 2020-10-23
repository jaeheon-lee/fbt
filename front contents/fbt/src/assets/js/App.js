export default {
    name : "app",
    props: {
      source: String
    },
    data: {
      icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
      drawer: null,
      user: null,
      notices: [
        {
          title:
            "Click MeClick MeClick MeClick MeClick MeClick MeClick MeClick MeClick Me"
        },
        { title: "Click Me" },
        { title: "Click Me" },
        { title: "Click Me 2" }
      ],
      offset: true,
      items: [
        {
          action: "sports_soccer",
          target: "team",
          title: "팀 선택",
          items: [
            {
              title: "FC 답십리"
            },
            {
              title: "FC 왕십리"
            },
            { title: "FC 신답" }
          ]
        },
        {
          action: "receipt_long",
          title: "관리자 메뉴",
          target: "managerMenu",
          items: [
            { title: "투표" },
            { title: "매치" },
            { title: "용병" },
            { title: "양도" },
            { title: "일정" },
            { title: "관리" }
          ]
        },
        {
          action: "people_alt",
          target: "allTeamMemberMenu",
          title: "팀 메뉴",
          items: [
            { title: "팀홈" },
            { title: "투표" },
            { title: "일정" },
            { title: "활동" },
            { title: "알림" }
          ]
        },
        {
          action: "face",
          title: "개인 메뉴",
          target: "privateMenu",
          items: [
            { title: "홈" },
            { title: "개인 전체일정" },
            { title: "팀 찾기" },
            { title: "팀 만들기" },
            { title: "용병" },
            { title: "전체 알림" },
            { title: "개인정보" }
          ]
        }
      ]
    },
    mount () {
      alert('1')
    },
    methods: {}
  };