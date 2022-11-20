package kr.megaptera.wherewego;

import kr.megaptera.wherewego.interceptors.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class WhereWeGoApplication {
	@Value("${jwt.secret}")
	private String jwtSecret;

	public static void main(String[] args) {
		SpringApplication.run(WhereWeGoApplication.class, args);
	}

	// Spring Security customized setting
	@Bean
	public WebSecurityCustomizer ignoringCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**");
	}

	// CORS 이슈 관련
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(authenticationInterceptor());
			}
		};
	}

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor(jwtUtil());
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(jwtSecret);
	}

	// PasswordEncoder(Argon2)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Argon2PasswordEncoder();
	}

	// third party login
	@Bean
	public KakaoLoginUtil kakaoLoginUtil() {
		return new KakaoLoginUtil();
	}

	@Bean
	public NaverLoginUtil naverLoginUtil() {
		return new NaverLoginUtil();
	}
}
