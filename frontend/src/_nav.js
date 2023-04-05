export default [
  {
    component: 'CNavItem',
    name: 'Dashboard',
    to: '/dashboard',
    icon: 'cil-speedometer',
    badge: {
      color: 'primary',
      text: 'NEW',
    },
  },
  {
    component: 'CNavTitle',
    name: 'Main',
  },
  {
    component: 'CNavGroup',
    name: 'Member',
    to: '/',
    icon: 'cil-star',
    items: [
      {
        component: 'CNavItem',
        name: 'Login',
        to: '/login',
      },
      {
        component: 'CNavItem',
        name: 'Register',
        to: '/register',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Api',
    to: '/',
    icon: 'cil-star',
    items: [
      {
        component: 'CNavItem',
        name: 'Covid',
        to: '/covid',
      },
      {
        component: 'CNavItem',
        name: 'Weather',
        to: '/weather',
      },
      {
        component: 'CNavItem',
        name: 'Movie',
        to: '/movie',
      },
      {
        component: 'CNavItem',
        name: 'WebRTC',
        to: '/webrtc',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Board',
    to: '/',
    icon: 'cil-star',
    items: [
      {
        component: 'CNavItem',
        name: 'Board',
        to: '/board',
      },
      {
        component: 'CNavItem',
        name: 'BoardWrite',
        to: '/boardWrite',
      },
    ],
  },
  // {
  //   component: 'CNavItem',
  //   name: 'Download CoreUI',
  //   href: 'http://coreui.io/vue/',
  //   icon: { name: 'cil-cloud-download', class: 'text-white' },
  //   _class: 'bg-success text-white',
  //   target: '_blank'
  // },
  // {
  //   component: 'CNavItem',
  //   name: 'Try CoreUI PRO',
  //   href: 'http://coreui.io/pro/vue/',
  //   icon: { name: 'cil-layers', class: 'text-white' },
  //   _class: 'bg-danger text-white',
  //   target: '_blank'
  // }
]
