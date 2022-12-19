package kr.megaptera.wherewego.annotations;

import org.springframework.context.annotation.*;
import org.springframework.web.filter.*;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Import({MockMvcEncoding.Config.class})
public @interface MockMvcEncoding {
    class Config {
        @Bean
        public CharacterEncodingFilter characterEncodingFilter() {
            return new CharacterEncodingFilter("UTF-8", true);
        }
    }
}
