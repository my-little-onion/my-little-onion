import { api } from '@/services/index';
import { response } from '@/types/response';

export const postMessage = async (
  onionId: number,
  message: string,
): Promise<response> => {
  return api.post(`/chatgpt/prompt`, { onionId, message });
};
