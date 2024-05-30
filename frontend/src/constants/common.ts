export const COLOR = {
  /* color picker */
  red: '#B33434',
  orange: '#C47243',
  yellow: '#DEA12A',
  green: '#90B04A',
  skyblue: '#488BBB',
  purple200: '#5B59B3',
  purple100: '#9A61D2',
  pink: '#C161AC',
} as const;

export const COLOR_PALLETE = [
  COLOR.red,
  COLOR.yellow,
  COLOR.skyblue,
  COLOR.pink,
  COLOR.orange,
  COLOR.green,
  COLOR.purple100,
  COLOR.purple200,
];

export const DAY_IN_MONTHS = {
  MIN: 35,
  MAX: 42,
} as const;

export const DAYS_OF_WEEK = ['일', '월', '화', '수', '목', '금', '토'];

export const GETTING_STARTED_TEXT = {
  create: {
    title: '새로운 약속 생성하기',
    description: '친구들과 만날 중간장소를 추천받아 약속을 만들어요.',
    buttonText: '약속 생성하기',
    redirectURL: 'create',
  },
  join: {
    title: '약속 참여하기',
    description: '친구들이 전달한 초대코드를 입력하여 약속에 참여해요',
    buttonText: '약속 참여하기',
    redirectURL: 'join',
  },
  myInfo: {
    title: '내 약속 확인하기',
    description: '내 예정된 약속과 종료된 약속을 확인할 수 있어요.',
    buttonText: '약속 확인하기',
    redirectURL: 'info',
  },
} as const;
