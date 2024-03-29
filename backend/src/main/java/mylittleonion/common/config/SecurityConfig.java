package mylittleonion.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.service.AuthService;
import mylittleonion.api.auth.service.CustomOAuth2UserService;
import mylittleonion.common.filter.JWTAuthenticationFilter;
import mylittleonion.common.handler.OAuth2AuthenticationFailureHandler;
import mylittleonion.common.handler.OAuth2AuthenticationSuccessHandler;
import mylittleonion.common.util.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

 private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
 private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JWTProvider jwtProvider;
  private final CorsConfig corsConfig;
  private final AuthService authservice;
  private final CustomOAuth2UserService customOAuth2UserService;


  @Bean
  SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .requestMatchers(HttpMethod.GET).permitAll()
//            .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
//             .requestMatchers(new AntPathRequestMatcher("/oauth2/authorization/**")
//                 , new AntPathRequestMatcher("/kakao-oauth/**")

            // ).permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic(httpBasic ->
            httpBasic.disable()
        )
        .csrf(csrf ->
            csrf.disable()
        )

        .logout(logout ->
            logout.disable()
        )
        .formLogin(formLogin ->
            formLogin.disable()
        )
        .oauth2Login(oauth2Login -> oauth2Login
            .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                .baseUri("/oauth2/authorize")
            )
            .redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint
                .baseUri("/kakao-oauth/**")
            )
//            .successHandler(oAuth2AuthenticationSuccessHandler)
//            .failureHandler(oAuth2AuthenticationFailureHandler)
        )
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
//        .exceptionHandling(exceptionHandling -> exceptionHandling
//            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            .accessDeniedHandler(jwtAccessDeniedHandler)
//        )
        .addFilter(corsConfig.corsFilter())
        .addFilterBefore(new JWTAuthenticationFilter(authservice, jwtProvider),
            // 이 부분 확인
            UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }
}
