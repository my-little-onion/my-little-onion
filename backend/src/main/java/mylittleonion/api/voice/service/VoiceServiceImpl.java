package mylittleonion.api.voice.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.voice.dto.GetVoiceResponse;
import mylittleonion.api.voice.repository.VoiceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VoiceServiceImpl implements VoiceService {

  private final VoiceRepository voiceRepository;

  @Override
  public List<GetVoiceResponse> getVoices(Long onionId) {
    return voiceRepository.findVoiceResponsesByOnionId(onionId);
  }

}
