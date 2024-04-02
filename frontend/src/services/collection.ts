import { api } from '@/services/index';
import { collectionResponse } from '@/types/collection';

export const getCollections = async (): Promise<collectionResponse> => {
  return api.get(`/onion/book`);
};
