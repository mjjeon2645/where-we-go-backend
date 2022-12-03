package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
import org.springframework.security.crypto.password.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

@Entity
@Table(name = "USERS")
public class User {
    public static final String UNREGISTERED = "unregistered";
    public static final String REGISTERED = "registered";
    public static final String DEACTIVATED = "deactivated";

    @Id
    @GeneratedValue
    private Long id;

    private String encodedPassword;

    private String email;

    private String nickname;

    private String socialLoginId;

    private String authBy;

    private String state;

    @Embedded
    @ElementCollection
    private List<Bookmark> bookmarks = new ArrayList<>();

    public static User fake(String email) {
        return new User(1L, "encodedPassword", email, "nickname", "socialLoginId",
            "kakao", User.UNREGISTERED, List.of());
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
                String socialLoginId, String authBy, String state, List<Bookmark> bookmarks) {
        this.encodedPassword = passwordForSocialLogin;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
        this.bookmarks = bookmarks;
    }

    public User(Long id, String encodedPassword, String email, String nickname,
                String socialLoginId, String authBy, String state, List<Bookmark> bookmarks) {
        this.id = id;
        this.encodedPassword = encodedPassword;
        this.email = email;
        this.nickname = nickname;
        this.socialLoginId = socialLoginId;
        this.authBy = authBy;
        this.state = state;
        this.bookmarks = bookmarks;
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

    public List<Bookmark> bookmarks() {
        return bookmarks;
    }

    public void register(String nickname) {
        this.nickname = nickname;
        this.state = User.REGISTERED;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void addBookmark(List<Bookmark> bookmarks, Long placeId) {
        bookmarks.add(new Bookmark(placeId));
    }

    public void removeBookmark(List<Bookmark> bookmarks, Long placeId) {
        Bookmark filtered = bookmarks.stream()
            .filter(bookmark -> bookmark.getPlaceId().equals(placeId))
            .findFirst().orElseThrow();

        bookmarks.remove(filtered);
    }

    public UserDto toDto() {
        return new UserDto(id, email, nickname, socialLoginId, authBy, state);
    }
}
