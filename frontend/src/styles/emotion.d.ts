import '@emotion/react';
import { ColorsTypes, FontSizeTypes, BordersTypes } from './theme';

declare module '@emotion/react' {
  export interface Theme {
    color: ColorsTypes;
    fontSize: FontSizeTypes;
    border: BordersTypes;
  }
}
