package kr.megaptera.wherewego.models;

import org.springframework.security.crypto.password.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String encodedPassword;

    private String email;

    private String nickname;

    private String socialLoginId;

    private String authBy;

    public static User fake(String email) {
        return new User(1L, "", email, "nickname", "socialId", "kakao");
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }

    public Long getId() {
        return id;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public String getAuthBy() {
        return authBy;
    }

    public User() {
    }

    public User(Long id, String encodedPassword, String email, String nickname,
                String socialLoginId, String authBy) {
        this.id = id;
        this.encodedPassword = encodedPassword;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
    }
}
