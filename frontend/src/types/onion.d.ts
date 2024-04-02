import { response } from '@/types/response';

export interface onion {
  onionCategoryId: number;
  onionId: number;
  onionName: string;
  onionLevel: number;
  isFinal: boolean;
  voiceNumber: number;
}

export interface onionResponse extends response {
  data: onion[];
}
