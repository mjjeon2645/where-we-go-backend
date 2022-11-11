package kr.megaptera.wherewego.backdoor;

import org.springframework.jdbc.core.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  public BackdoorController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("setup-place-database")
  public String setupPlaceDataBase() {
    // 순서. (1) 기존 데이터 삭제, (2) 내가 원하는 데이터로 초기화

    jdbcTemplate.execute("DELETE FROM place");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 0)",
        "덕수궁", 37.565804, 126.975146, "서울특별시 중구 세종대로 99", "서울", "중구",
        "교육/체험", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20150831_46/1440997732945CYxgU_JPEG/11571730_0.jpg",
        "https://ldb-phinf.pstatic.net/20150831_46/1440997732945CYxgU_JPEG/11571730_0.jpg",
        "https://ldb-phinf.pstatic.net/20150831_46/1440997732945CYxgU_JPEG/11571730_0.jpg",
        "https://megaptera.kr/", "010-5634-1111", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 1)",
        "국립중앙박물관", 37.523850, 126.980470, "서울특별시 용산구 서빙고로 137", "서울", "용산구",
        "교육/체험", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20211213_69/1639369516747nsHjE_JPEG/1%BA%CE_%BB%F5%B7%D3%B0%D4_%B0%FC%C2%FB%C7%D8%BF%E4_%C3%A2%C0%DB_%B3%EE%C0%CC%C5%CD_%C3%BC%C7%E8.jpg",
        "https://ldb-phinf.pstatic.net/20211213_69/1639369516747nsHjE_JPEG/1%BA%CE_%BB%F5%B7%D3%B0%D4_%B0%FC%C2%FB%C7%D8%BF%E4_%C3%A2%C0%DB_%B3%EE%C0%CC%C5%CD_%C3%BC%C7%E8.jpg",
        "https://ldb-phinf.pstatic.net/20200923_277/16008263834047Gv9q_JPEG/fVp8_jS4uGJkPlbsOl6KtQ-7.JPG.jpg",
        "https://megaptera.kr/", "010-5634-2222", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(2, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 2)",
        "서울숲 공원", 37.544387, 127.037442, "서울특별시 성동구 뚝섬로 273", "서울", "성동구",
        "자연", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20200728_296/1595899415265D5A3d_JPEG/%B0%A1%C1%B7%B8%B6%B4%E71.jpg",
        "https://ldb-phinf.pstatic.net/20140407_95/13968547640735qgog_JPEG/%BC%AD%BF%EF_%BC%AD%BF%EF%BD%A3_1.JPG?type=m500_500",
        "https://ldb-phinf.pstatic.net/20200728_75/159589941595285OWD_JPEG/%C0%BA%C7%E0%B3%AA%B9%AB%BD%A3.jpg",
        "https://megaptera.kr/", "010-5634-3333", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 3)",
        "양양 쏠비치", 38.086867, 128.665343, "강원도 양양군 손양면 선사유적로 678", "강원", "양양군",
        "숙박/캠핑", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20200904_299/1599197585652f8fqL_JPEG/S5tsAEB34mG3LEIdKzkZ-jKc.JPG.jpg",
        "https://ldb-phinf.pstatic.net/20200904_299/1599197585652f8fqL_JPEG/S5tsAEB34mG3LEIdKzkZ-jKc.JPG.jpg",
        "https://ldb-phinf.pstatic.net/20200527_4/1590560720498EP2aL_JPEG/%BC%B1%BC%C2%BD%C3%B3%D7%B8%B601.JPG",
        "https://megaptera.kr/", "010-5634-4444", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(4, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 4)",
        "과천 서울랜드", 37.434156, 127.020126, "경기도 과천시 광명로 181", "경기", "과천시",
        "자연", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20201022_40/1603343070859XaMWM_JPEG/%BC%AD%BF%EF%B7%A3%B5%E5_%B0%A1%C0%BB_1000.jpg",
        "https://ldb-phinf.pstatic.net/20201022_40/1603343070859XaMWM_JPEG/%BC%AD%BF%EF%B7%A3%B5%E5_%B0%A1%C0%BB_1000.jpg",
        "https://ldb-phinf.pstatic.net/20150206_66/1423206267132Bgi1r_JPEG/%B0%E6%B1%E2_%B0%FA%C3%B5%BC%AD%BF%EF%B7%A3%B5%E5_16.JPG?type=m500_500",
        "https://megaptera.kr/", "010-5634-5555", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(5, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 5)",
        "킹덤키즈풀빌라", 37.7059458, 127.5402107, "경기도 가평군 설악면 미사리 320-1", "경기", "가평군",
        "숙박/캠핑", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20200719_256/1595136858289JezIJ_JPEG/0IUjabtxT220N1U3ORX3C9jO.jpg",
        "https://ldb-phinf.pstatic.net/20200719_55/1595136906668wqXc2_JPEG/VyKA8Zaj6lipHTqW_bAV7Z38.jpg",
        "https://ldb-phinf.pstatic.net/20200719_206/15951368647954SmtO_JPEG/MobEYFY7Eev4uotfItfofgpr.jpg",
        "https://megaptera.kr/", "010-5634-6666", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(6, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 6)",
        "조이풀키즈엔시스펜싱클럽", 37.3140285, 127.1110547, "경기도 용인시 기흥구 보정로 32", "경기", "기흥구",
        "스포츠/레저", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20180510_68/1525939447197nBVy5_JPEG/5gs5xk7QmiOL-vgLJZ2zZWZY.jpg",
        "https://myplace-phinf.pstatic.net/20200817_201/1597659343340hgCO8_JPEG/upload_86627ea5cb4fc6f92d99437428f26469.jpeg",
        "https://ldb-phinf.pstatic.net/20200506_69/1588756287606i4xnv_JPEG/TZXs30tzMBu5KvmbIpxLNfmH.jpg",
        "https://megaptera.kr/", "010-5634-7777", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(7, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 7)",
        "키즈카페 까블랑", 36.7621865, 126.9920311, "충청남도 아산시 용화동 483-6", "충청", "아산시",
        "키즈카페", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20150206_11/1423206935248b1BcO_JPEG/%B0%E6%BA%CF_%BE%C8%B5%BF%B5%B5%BB%EA%BC%AD%BF%F8_2.jpg?type=m500_500",
        "https://ldb-phinf.pstatic.net/20150206_191/1423206936272t04Ew_JPEG/%B0%E6%BA%CF_%BE%C8%B5%BF%B5%B5%BB%EA%BC%AD%BF%F8_5.jpg?type=m500_500",
        "https://ldb-phinf.pstatic.net/20210520_270/1621505183231h76Sv_JPEG/banner5.jpg",
        "https://megaptera.kr/", "010-5634-8888", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(8, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 8)",
        "키즈쿡", 37.2329127, 127.2132449, "경기도 용인시 처인구 중부대로 1522 2층 201호", "경기", "용인시",
        "키즈존 맛집", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20200707_46/1594126241001ku6fM_JPEG/cWSEwAmzeELyo6wmHDtsEhte.jpg",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160611_227%2Felegance_ksi_1465644396607Dh1L9_JPEG%2FDSC01031.JPG%23740x491",
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160705_126%2Fbmw850307_1467646837010pi58p_JPEG%2Fimage_5795767901467645228762.jpg%23960x960",
        "https://megaptera.kr/", "010-5634-9999", "possible", "possible", "impossible", "unchecked");

    jdbcTemplate.update("" +
            "INSERT INTO place(" +
            "id, name, latitude, longitude, full_address, sido, sigungu, " +
            "category, monday, tuesday, wednesday, thursday, friday, saturday, sunday, " +
            "first_image, second_image, third_image, homepage, phone, reservation, " +
            "parking, outside_food, nursing_room, place_id)" +
            " VALUES(9, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, 9)",
        "포스코 키즈콘서트", 37.5057406, 127.056107, "서울특별시 강남구 테헤란로 440", "서울", "강남구",
        "전시/공연", "월요일: 10:01~18:00", "화요일: 10:02~18:00", "수요일: 10:03~18:00",
        "목요일: 10:04~18:00", "금요일: 10:05~18:00", "토요일: 10:06~18:00", "일요일: 10:07~18:00",
        "https://ldb-phinf.pstatic.net/20181026_105/1540535000240RhwBH_PNG/8471LKUw092JqpeiebclFiv2.png",
        "https://user-images.githubusercontent.com/104840243/198858244-29a83802-3ebe-42c8-894a-151c0962b8da.png",
        "https://user-images.githubusercontent.com/104840243/198858249-0e5eb65b-1a68-4549-bace-b906aa550413.png",
        "https://megaptera.kr/", "010-5634-0000", "possible", "possible", "impossible", "unchecked");

    return "OK";
  }

  @GetMapping("setup-blog-review-database")
  public String setupBlogReviewDataBase() {
    // 순서. (1) 기존 데이터 삭제, (2) 내가 원하는 데이터로 초기화

    jdbcTemplate.execute("DELETE FROM blog_review");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(0, 0, ?, ?, ?, ?, ?)",
        "봄이의 22개월 기록. 운 좋게 피해갔던 코로나 19", "작성자0", "2022. 4. 21.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222706901645");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(1, 1, ?, ?, ?, ?, ?)",
        "내돈내산 3월 가족여행 1탄. 양양 쏠비치 할인가격으로 예약하는법(feat. 근로복지서비스)", "작성자1", "2022. 3. 29.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222685946251");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(2, 2, ?, ?, ?, ?, ?)",
        "내돈내산! 아기텐트 코아코아 아이스크림마켓 키즈텐트", "작성자2", "2022. 3. 6.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222665642402");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(3, 3, ?, ?, ?, ?, ?)",
        "봄이의 21개월 기록. 하루하루가 경이로움을 넘어 경악스러울 정도 ㅎㅎ", "작성자3", "2022. 2. 22.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222654117177");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(4, 5, ?, ?, ?, ?, ?)",
        "20개월 아기 일상&발달 기록", "작성자4", "2022. 2. 11.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222645278987");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(5, 6, ?, ?, ?, ?, ?)",
        "21개월 아기와 1박2일로 다녀온 가평 프리미엄키즈풀빌라. 우리 또 여행가자~", "작성자5", "2022. 2. 9.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222642606101");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(6, 7, ?, ?, ?, ?, ?)",
        "20개월 아기와 다녀온 송도 아틀란티스 키즈카페", "작성자6", "2022. 1. 26.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222631669831");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(7, 7, ?, ?, ?, ?, ?)",
        "18개월 아기의 18춘기 일상(feat. 엄마 속 부글부글)", "작성자7", "2021. 12. 6.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222588574093");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(8, 8, ?, ?, ?, ?, ?)",
        "[강서/발산] 분위기 좋은 발산역 한우 맛집 설화랑", "작성자8", "2019. 2. 15.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/221466705368");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(9, 9, ?, ?, ?, ?, ?)",
        "쌀쌀해진 날씨, 우리 아기내복으로 주문한 아가방 신생아내복!", "작성자9", "2020. 10. 7.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222109627588");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(10, 9, ?, ?, ?, ?, ?)",
        "[강서/발산] 분위기 좋은 발산역 한우 맛집 설화랑", "작성자10", "2019. 2. 15.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/221466705368");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(11, 9, ?, ?, ?, ?, ?)",
        "20개월 아기와 다녀온 송도 아틀란티스 키즈카페", "작성자11", "2022. 1. 26.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222631669831");

    jdbcTemplate.update("" +
            "INSERT INTO blog_review(" +
            "id, place_id, title, author, date, image_source, url)" +
            " VALUES(12, 9, ?, ?, ?, ?, ?)",
        "18개월 아기의 18춘기 일상(feat. 엄마 속 부글부글)", "작성자12", "2021. 12. 6.",
        "https://user-images.githubusercontent.com/104840243/198858240-ef8949d2-c294-4ab8-8a4c-fc42717bee8e.png",
        "https://blog.naver.com/angel2645/222588574093");

    return "OK";
  }

  @GetMapping("setup-user-review-database")
  public String setupUserReviewDataBase() {
    // 순서. (1) 기존 데이터 삭제, (2) 내가 원하는 데이터로 초기화

    jdbcTemplate.execute("DELETE FROM user_review");

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(0, 0, 0, ?, ?, ?, ?, ?)",
        5L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(1, 0, 1, ?, ?, ?, ?, ?)",
        3L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(2, 0, 2, ?, ?, ?, ?, ?)",
        4L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(3, 0, 3, ?, ?, ?, ?, ?)",
        2L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(4, 0, 3, ?, ?, ?, ?, ?)",
        3L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), true);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(5, 1, 4, ?, ?, ?, ?, ?)",
        1L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(6, 2, 5, ?, ?, ?, ?, ?)",
        2L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(7, 3, 6, ?, ?, ?, ?, ?)",
        5L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(8, 4, 7, ?, ?, ?, ?, ?)",
        4L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), false);

    jdbcTemplate.update("" +
            "INSERT INTO user_review(" +
            "id, place_id, user_id, rate, body, date_of_visit, created_at, is_deleted)" +
            " VALUES(9, 5, 8, ?, ?, ?, ?, ?)",
        5L, "이 장소 정말 아이랑 가기 좋아요!!", "2022-04-01",
        LocalDateTime.of(2022, 10, 8, 10, 43, 0, 0), true);

    return "OK";
  }
}
