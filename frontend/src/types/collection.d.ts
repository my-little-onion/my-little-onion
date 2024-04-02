import { response } from '@/types/response';

export interface collection {
  id: 0;
  have: true;
  onionCategoryName: string;
  onionCategoryDetail: string;
}

export interface collectionResponse extends response {
  data: collection[];
}
