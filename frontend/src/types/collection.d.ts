import { response } from '@/types/response';

export interface collection {
  id: number;
  have: boolean;
  onionCategoryName: string;
  onionCategoryDetail: string;
}

export interface collectionResponse extends response {
  data: collection[];
}
