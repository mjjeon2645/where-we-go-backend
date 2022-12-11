package kr.megaptera.wherewego.backdoor;

import org.springframework.jdbc.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("admin-backdoor")
@Transactional
public class AdminBackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public AdminBackdoorController(JdbcTemplate jdbcTemplate,
                                   PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-admin")
    public String setupAdmin() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM admin");

        // 2. 원하는 데이터 세팅
        jdbcTemplate.update("" +
                "INSERT INTO admin(" +
                "id, social_login_id, encoded_password, name, employee_identification_number, " +
                "profile_image, created_at" +
                ") " +
                "VALUES(1, 'tester123', ?, '전민지', 1212, " +
                "'https://avatars.githubusercontent.com/u/104840243?v=4', ?)",
            passwordEncoder.encode("Tester123!"), LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));

        return "ok";
    }

    @GetMapping("setup-no-place")
    public String setupNoPlace() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM place");

        return "ok";
    }

    @GetMapping("setup-five-places")
    public String setupFivePlaces() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM place");

        // 2. 원하는 데이터 세팅
        jdbcTemplate.update("" +
                "INSERT INTO place(" +
                "id, name, latitude, longitude, full_address, sido, sigungu, " +
                "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
                "first_image, second_image, third_image, reservation, parking, " +
                "outside_food, nursing_room, phone, homepage) " +
                "VALUES(1, '오형제네', 36.5178215598647, 127.26268234061, '세종특별자치시 보람로 111호', " +
                "'세종', '', '스포츠/레저', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
                "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
                "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
                "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
                "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
                "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
                "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
                "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(2, '준형이네', 37.4691346876838, 126.707361571511, '인천 남동구 동암남로 111호', " +
            "'인천', '남동구', '키즈카페', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(3, '재원이네', 37.5417452308325, 127.086431412551, '서울 광진구 자양로 111호', " +
            "'서울', '광진구', '스포츠/레저', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(4, '승준이네', 37.010815317286, 126.533340903549, '충남 당진시 석문면 111호', " +
            "'충남', '당진시', '스포츠/레저', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(5, '이누네', 36.1019811641475, 127.480275892497, '충남 금산군 이누네', " +
            "'충남', '금산군', '교육/체험', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        return "ok";
    }

    @GetMapping("setup-no-user")
    public String setupNoUser() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM user_bookmarks");
        jdbcTemplate.execute("DELETE FROM users");

        return "ok";
    }

    @GetMapping("setup-two-users")
    public String setupTwoUsers() {
        // 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM user_bookmarks");
        jdbcTemplate.execute("DELETE FROM users");

        jdbcTemplate.execute("DELETE FROM user_review");

        // 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM place");

        // 원하는 데이터 세팅
        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(1, '오형제네', 36.5178215598647, 127.26268234061, '세종특별자치시 보람로 111호', " +
            "'세종', '', '스포츠/레저', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(2, '준형이네', 37.4691346876838, 126.707361571511, '인천 남동구 동암남로 111호', " +
            "'인천', '남동구', '키즈카페', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, reservation, parking, " +
            "outside_food, nursing_room, phone, homepage) " +
            "VALUES(3, '재원이네', 37.5417452308325, 127.086431412551, '서울 광진구 자양로 111호', " +
            "'서울', '광진구', '스포츠/레저', '월요일: 15:04~19:04', '화요일: 15:04~19:04', " +
            "'수요일: 15:04~19:04', '목요일: 15:04~19:04', '금요일: 15:04~19:04', " +
            "'토요일: 12:04~20:04', '일요일: 12:04~20:04', " +
            "'https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png', " +
            "'https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png', " +
            "'possible', 'impossible', 'possible', 'unchecked', '010-000-1234', " +
            "'HomePage Url')");

        // 원하는 데이터 세팅
        jdbcTemplate.update("" +
            "INSERT INTO users(" +
            "id, encoded_password, email, nickname, social_login_id, auth_by, state, " +
            "created_at) " +
            "VALUES(1, ?, 'tester1@tester.com', '민지룽룽', '12345678', " +
            "'kakao', 'registered', ?)", passwordEncoder.encode("Tester123!"),
            LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0));

        jdbcTemplate.update("" +
                "INSERT INTO user_bookmarks(" +
                "user_id, place_id) " +
                "VALUES(1, 1)");

        jdbcTemplate.update("" +
            "INSERT INTO user_bookmarks(" +
            "user_id, place_id) " +
            "VALUES(1, 2)");

        jdbcTemplate.update("" +
                "INSERT INTO users(" +
                "id, encoded_password, email, nickname, social_login_id, auth_by, state, " +
                "created_at) " +
                "VALUES(2, ?, 'tester2@tester.com', '룽룽탱', '98765432', " +
                "'naver', 'registered', ?)", passwordEncoder.encode("Tester123!"),
            LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0));

        jdbcTemplate.update("" +
            "INSERT INTO user_bookmarks(" +
            "user_id, place_id) " +
            "VALUES(2, 1)");

        jdbcTemplate.update("" +
            "INSERT INTO user_bookmarks(" +
            "user_id, place_id) " +
            "VALUES(2, 2)");

        jdbcTemplate.update("" +
            "INSERT INTO user_bookmarks(" +
            "user_id, place_id) " +
            "VALUES(2, 3)");

        return "ok";
    }

    @GetMapping("reset-all")
    public String resetAll() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM user_bookmarks");
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM user_review");
        jdbcTemplate.execute("DELETE FROM child");
        jdbcTemplate.execute("DELETE FROM place");

        return "ok";
    }

    @GetMapping("setup-no-review")
    public String setupNoReview() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM user_review");

        return "ok";
    }

    @GetMapping("setup-three-reviews")
    public String setupThreeReviews() {
        // 1. 기존 데이터 리셋
        jdbcTemplate.execute("DELETE FROM user_review");

        // 2. 원하는 데이터 추가
        jdbcTemplate.update("" +
                "INSERT INTO user_review(" +
                "id, place_id, user_id, rate, nickname, body, date_of_visit, " +
                "created_at, is_deleted) " +
                "VALUES(1, 1, 1, 5, '민지룽룽', '정말 좋았어요. 아이랑 같이가기 좋아요!!', " +
                "'2022-11-30', ?, false)", LocalDateTime.of(2022, 12, 8, 10, 43, 0, 0));

        jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, nickname, body, date_of_visit, " +
            "created_at, is_deleted) " +
            "VALUES(2, 2, 2, 3, '룽룽탱', '여기 완전 인생 장소예요 아이들도 좋아했답니다!', " +
            "'2022-12-01', ?, false)", LocalDateTime.of(2022, 12, 9, 10, 43, 0, 0));


        jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, nickname, body, date_of_visit, " +
            "created_at, is_deleted) " +
            "VALUES(3, 2, 1, 4, '민지룽룽', '다음번에 또 오고싶은 곳. 후회 없어요 정말 좋아요!', " +
            "'2022-12-05', ?, false)", LocalDateTime.of(2022, 12, 10, 10, 43, 0, 0));

        return "ok";
    }
}
