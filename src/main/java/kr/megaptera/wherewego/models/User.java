package kr.megaptera.wherewego.models;

import org.springframework.security.crypto.password.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String encodedPassword;

    private String nickName;

    public User() {
    }

    public User(Long id, String email, String encodedPassword, String nickName) {
        this.id = id;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.nickName = nickName;
    }

    public Long id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String encodedPassword() {
        return encodedPassword;
    }

    public String nickName() {
        return nickName;
    }

    public static User fake(String email) {
        return new User(1L, email, "Tester1234", "nickname");
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.encodedPassword = passwordEncoder.encode(password);
    }
}
