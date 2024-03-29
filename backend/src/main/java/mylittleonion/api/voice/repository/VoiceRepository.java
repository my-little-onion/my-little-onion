package mylittleonion.api.voice.repository;

import java.util.List;
import mylittleonion.common.entity.Onion;
import mylittleonion.common.entity.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {

  List<Voice> findAllVoiceByOnion(Onion onion);
}
