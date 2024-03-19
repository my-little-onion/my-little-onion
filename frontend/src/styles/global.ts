import { css } from '@emotion/react';

import PFStardustBold from '/fonts/PFStardustS-Bold.woff';
import PFStardust from '/fonts/PFStardustS.woff';

import theme from '@/styles/theme';

export const globalStyles = css`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    line-height: 1;
    font-family: 'PFStardust';
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

  @font-face {
    font-family: 'PFStardust';
    src:
      local('PFStardust'),
      url(${PFStardust}) format('woff');
    font-weight: normal;
    font-style: normal;
  }

  @font-face {
    font-family: 'PFStardustBold';
    src:
      local('PFStardustBold'),
      url(${PFStardustBold}) format('woff');
    font-weight: normal;
    font-style: normal;
  }
`;
