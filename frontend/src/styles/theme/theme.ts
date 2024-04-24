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

  blue900: '#172554',
  blue800: '#1e40af',
  blue700: '#1d4ed8',
  blue600: '#2563eb',
  blue500: '#3b82f6',
  blue400: '#60a5fa',
  blue300: '#93c5fd',
  blue200: '#bfdbfe',
  blue100: '#dbeafe',

  red300: '#c50000',
  red200: '#ea0000',
  red100: '#fff2f2',
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
  xLarge: '32px',
  large: '16px',
  medium: '8px',
  small: '4px',
} as const;

const boxShadow = {
  /* offset-y value exist */
  shadow1: '0 1px 2px 0 rgb(0, 0, 0, 0.05)',
  shadow2: '0 1px 3px 0 rgb(0, 0, 0, 0.1), 0 1px 2px -1px rgb(0, 0, 0, 0.1)',
  shadow3: '0 4px 6px -1px rgb(0, 0, 0, 0.1), 0 2px 4px -2px rgb(0, 0, 0, 0.1)',
  shadow4: '0 10px 15px -3px rgb(0, 0, 0, 0.1), 0 4px 6px -4px rgb(0, 0, 0, 0.1)',
  shadow5: '0 20px 25px -5px rgb(0, 0, 0, 0.1), 0 8px 10px -6px rgb(0, 0, 0, 0.1)',
  shadow6: '0 25px 50px -12px rgb(0, 0, 0, 0.25)',
  shadow7: 'inset 0 2px 4px 0 rgb(0, 0, 0, 0.05)',

  /* no offset-y value, only blur-radius */
  blur100: '0px 0px 4px 0px rgba(0, 0, 0, 0.15)',
  blur200: '0px 0px 8px 0px rgba(0, 0, 0, 0.20)',
  blur300: '0px 0px 12px 0px rgba(0, 0, 0, 0.20)',
} as const;

const spacing = {
  spacing1: '0',
  spacing2: '4px',
  spacing3: '8px',
  spacing4: '16px',
  spacing5: '24px',
  spacing6: '32px',
  spacing7: '48px',
  spacing8: '64px',
  spacing9: '96px',
  spacing10: '128px',
} as const;

const zIndex = {
  overlayTop: 4,
  overlayHigh: 3,
  overlayMiddle: 2,
  overlayBottom: 1,
} as const;

export const Theme = {
  color,
  text,
  heading,
  borderRadius,
  boxShadow,
  spacing,
  zIndex,
};
