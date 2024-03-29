import { api } from '@/services/index';
import { onionResponse } from '@/types/onion';
import { response } from '@/types/response';

export const getOnions = async (): Promise<onionResponse> => {
  return api.get(`/onion`);
};

export const createOnion = async (onionName: string): Promise<response> => {
  return api.post(`/onion`, { onionName });
};

export const deleteOnion = async (onionId: number): Promise<response> => {
  return api.delete(`/onion?onionId=${onionId}`);
};
