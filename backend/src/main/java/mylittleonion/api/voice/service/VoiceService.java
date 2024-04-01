package mylittleonion.api.voice.service;

import java.util.List;
import mylittleonion.api.voice.dto.GetVoiceResponse;

public interface VoiceService {

  List<GetVoiceResponse> getVoices(Long onionId);
}
