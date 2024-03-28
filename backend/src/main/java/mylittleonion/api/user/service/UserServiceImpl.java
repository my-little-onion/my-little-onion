package mylittleonion.api.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.user.repository.UserRepository;
import mylittleonion.common.entity.User;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }
}
