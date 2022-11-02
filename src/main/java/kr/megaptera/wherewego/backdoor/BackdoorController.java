package kr.megaptera.wherewego.backdoor;

import org.springframework.jdbc.core.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  public BackdoorController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("setup-place-database")
  public String setupDatabase() {
    // 순서. (1) 기존 데이터 삭제, (2) 내가 원하는 데이터로 초기화

    jdbcTemplate.execute("DELETE FROM place");

    jdbcTemplate.update("" +
        "INSERT INTO place(" +
        "id, name, latitude, longitude, full_address, sido, sigungu, " +
        "category, monday, tuesday, wednesday, thursday, friday, saturday, " +
            "sunday, first_image, second_image, third_image, place_id)" +
        " VALUES(1, ?, ?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, ?, ?, ?, " +
        "?, ?, ?)",
        "과천 서울랜드", 37.434156, 127.020126, "경기도 과천시 광명로 181", "경기도", "과천시",
        "자연", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00",
        "일요일: 10:07~18:00",
        "img", "img", "img", 1);

    return "OK";
  }
}
