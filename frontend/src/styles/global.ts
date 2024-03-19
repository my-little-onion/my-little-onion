import { css } from '@emotion/react';

import PFStardustBold from '/fonts/PFStardustS-Bold.woff';
import PFStardust from '/fonts/PFStardustS.woff';

export const globalStyles = css`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    line-height: 1;
    font-family: 'PFStardust';
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
