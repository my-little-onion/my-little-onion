import { api } from '@/services/index';
import { speechToTextResponse } from '@/types/speech';

export const postSpeechToText = async (
  onionId: number,
  stt: string,
): Promise<speechToTextResponse> => {
  return api.post(`/chatgpt/prompt`, { onionId, stt });
};
