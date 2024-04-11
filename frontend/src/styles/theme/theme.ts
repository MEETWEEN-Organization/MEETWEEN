const color = {
  black: 'black',

  gray800: '#27272a',
  gray700: '#3f3f46',
  gray600: '#52525b',
  gray500: '#71717a',
  gray400: '#a1a1aa',
  gray300: '#d4d4d8',
  gray200: '#e4e4e7',
  gray100: '#f4f4f5',

  white: 'white',

  blue800: '#1e40af',
  blue700: '#1d4ed8',
  blue600: '#2563eb',
  blue500: '#3b82f6',
  blue400: '#60a5fa',
  blue300: '#93c5fd',
  blue200: '#bfdbfe',
  blue100: '#dbeafe',

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

const text = {
  large: {
    fontSize: '18px',
    lineHeight: '28px',
  },
  medium: {
    fontSize: '16px',
    lineHeight: '24px',
  },
  small: {
    fontSize: '14px',
    lineHeight: '20px',
  },
  xSmall: {
    fontSize: '12px',
    lineHeight: '20px',
  },
} as const;

const heading = {
  xxLarge: {
    fontSize: '40px',
    lineHeight: '52px',
  },
  xLarge: {
    fontSize: '36px',
    lineHeight: '44px',
  },
  large: {
    fontSize: '32px',
    lineHeight: '40px',
  },
  medium: {
    fontSize: '28px',
    lineHeight: '36px',
  },
  small: {
    fontSize: '24px',
    lineHeight: '32px',
  },
  xSmall: {
    fontSize: '20px',
    lineHeight: '28px',
  },
} as const;

const borderRadius = {
  small: '4px',
  medium: '8px',
  large: '16px',
} as const;

const boxShadow = {
  shadow1: 'rgba(100, 100, 111, 0.2) 0px 7px 29px 0px',
  shadow2: 'rgba(99, 99, 99, 0.2) 0px 2px 8px 0px',
  shadow3: 'rgba(0, 0, 0, 0.24) 0px 3px 8px',
  shadow4: 'rgba(0, 0, 0, 0.35) 0px 5px 15px',
  shadow5: 'rgba(0, 0, 0, 0.1) 0px 4px 12px',
  shadow6: 'rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 2px 6px 2px',
  shadow7: 'rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px',
  shadow8: 'rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px',
  shadow9: 'rgba(50, 50, 93, 0.25) 0px 30px 60px -12px, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px',
  shadow10: 'rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px',
} as const;

const margin = {
  spacing01: '0',
  spacing02: '4px',
  spacing03: '8px',
  spacing04: '16px',
  spacing05: '24px',
  spacing06: '32px',
  spacing07: '48px',
  spacing08: '64px',
  spacing09: '96px',
  spacing10: '128px',
} as const;

const zIndex = {
  overlayTop: 10,
  overlayHigh: 8,
  overlayMiddle: 6,
  overlayBottom: 4,
} as const;

export const Theme = {
  color,
  text,
  heading,
  borderRadius,
  boxShadow,
  margin,
  zIndex,
};
