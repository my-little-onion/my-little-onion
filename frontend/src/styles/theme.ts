import { Theme } from '@emotion/react';

const color = {
  white: '#fff',
  black: '#000',
  primary: '#a1c4fd',
  whitesmoke: '#f1f1f1',
};

const fontSize = {
  small: '14px',
  medium: '16px',
  title: '42px',
  subtitle: '20px',
};
const border = {
  primary: '#e5e7eb',
};

const zIndex = {
  title: 101,
  titleCharacter: 100,
  5: 5,
  4: 4,
  3: 3,
  2: 2,
  1: 1,
};

export type ColorsTypes = typeof color;
export type FontSizeTypes = typeof fontSize;
export type BordersTypes = typeof border;
export type ZIndexTypes = typeof zIndex;

const theme: Theme = {
  color,
  fontSize,
  border,
  zIndex,
};

export default theme;
