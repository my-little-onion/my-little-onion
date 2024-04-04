import { response } from '@/types/response';

export interface speechToTextResponse extends response {
  data: {
    canEvolve: boolean;
    categoryId: number;
  };
}
