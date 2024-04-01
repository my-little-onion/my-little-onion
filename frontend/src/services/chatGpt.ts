import { api } from '@/services/index';
import { response } from '@/types/response';

export const postVoiceMessage = async (
  onionId: number,
  stt: string,
): Promise<response> => {
  return api.post(`/chatgpt/prompt`, { onionId, stt });
};
