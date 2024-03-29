import { api } from '@/services/index';
import { onionResponse } from '@/types/onion';

export const getOnions = async (): Promise<onionResponse> => {
  return api.get(`/onion`);
};
