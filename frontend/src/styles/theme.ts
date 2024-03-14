import { Theme } from '@emotion/react';

const color = {
  black: '#000',
  primary: '#a1c4fd',
  gray500: '#555',
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

export type ColorsTypes = typeof color;
export type FontSizeTypes = typeof fontSize;
export type BordersTypes = typeof border;

const theme: Theme = {
  color,
  fontSize,
  border,
};

export default theme;
