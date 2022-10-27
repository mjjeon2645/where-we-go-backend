package kr.megaptera.wherewego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;

@SpringBootApplication
public class WhereWeGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereWeGoApplication.class, args);
	}

	// Spring Security customized setting
	@Bean
	public WebSecurityCustomizer ignoringCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**");
	}
}
