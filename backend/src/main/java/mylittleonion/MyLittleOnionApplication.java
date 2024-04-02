package mylittleonion;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@OpenAPIDefinition(servers = {
    @Server(url = "https://j10a105.p.ssafy.io/api/", description = "Default Server URL")})
@EnableJpaAuditing
public class MyLittleOnionApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyLittleOnionApplication.class, args);
  }

}
