import '@emotion/react';
import {
  ZIndexTypes,
  ColorsTypes,
  FontSizeTypes,
  BordersTypes,
} from '@/styles/theme';

declare module '@emotion/react' {
  export interface Theme {
    color: ColorsTypes;
    fontSize: FontSizeTypes;
    border: BordersTypes;
    zIndex: ZIndexTypes;
  }
}
