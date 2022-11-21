package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
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

    private String state;

    public static User fake(String email) {
        return new User(1L, "encodedPassword", email, "nickname", "socialId", "kakao", "unregistered");
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }

    public User() {
    }

    public User(String passwordForSocialLogin, String email, String nickname,
                String socialLoginId, String authBy, String state) {
        this.encodedPassword = passwordForSocialLogin;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
    }

    public User(Long id, String encodedPassword, String email, String nickname,
                String socialLoginId, String authBy, String state) {
        this.id = id;
        this.encodedPassword = encodedPassword;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
    }

    public Long id() {
        return id;
    }

    public String encodedPassword() {
        return encodedPassword;
    }

    public String email() {
        return email;
    }

    public String nickname() {
        return nickname;
    }

    public String socialLoginId() {
        return socialLoginId;
    }

    public String authBy() {
        return authBy;
    }

    public String state() {
        return state;
    }

    public void register(UserInformationDto userInformationDto) {
        String changedNickname = userInformationDto.getNickname();

        this.nickname = changedNickname;
        this.state = "registered";
    }

    public void changeNickname(UserInformationDto userInformationDto) {
        String changedNickname = userInformationDto.getNickname();

        this.nickname = changedNickname;
    }
}
