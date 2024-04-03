import { css } from '@emotion/react';

import Galmuri from '/fonts/Galmuri9.woff2';

import theme from '@/styles/theme';

export const globalStyles = css`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    line-height: 1;
    font-family: 'Galmuri';
  }

  body {
    width: auto;
    max-width: 480px;
    min-height: 100svh;
    margin: 0 auto;
    padding: 0;
    background-color: ${theme.color.white};
    position: relative;
    box-shadow: 0 0 25px ${theme.color.whitesmoke};
  }

  img {
    -webkit-user-drag: none;
  }

  &::-webkit-scrollbar {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 15px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: gray;
    border-radius: 15px;
  }

  @font-face {
    font-family: 'Galmuri';
    src:
      local('Galmuri'),
      url(${Galmuri}) format('woff2');
    font-weight: normal;
    font-style: normal;
  }
`;
