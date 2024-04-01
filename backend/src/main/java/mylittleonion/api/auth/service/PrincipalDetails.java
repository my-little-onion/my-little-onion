package mylittleonion.api.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import mylittleonion.common.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class PrincipalDetails implements OAuth2User {
  private User user;
  private Map<String, Object> attributes;

  public PrincipalDetails(User user, Map<String, Object> attributes) {
    this.user = user;
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(() -> "USER");
    return authorities;
  }

  @Override
  public String getName() {
    return null;
  }
}
