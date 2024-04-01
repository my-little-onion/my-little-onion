package mylittleonion.api.voice.repository;

import java.util.List;
import mylittleonion.api.voice.dto.GetVoiceResponse;
import mylittleonion.common.entity.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {

  @Query("SELECT NEW mylittleonion.api.voice.dto.GetVoiceResponse(v.contents) FROM Voice v WHERE v.onion.id = :onionId")
  List<GetVoiceResponse> findVoiceResponsesByOnionId(Long onionId);
}
