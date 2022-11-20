package kr.megaptera.wherewego.utils;

import com.google.gson.*;
import kr.megaptera.wherewego.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

import java.util.*;

@Transactional
public class KakaoLoginUtil {
    @Value("${kakao.api-key}")
    private String apiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    private Map<String, String> kakaoUserInformation;

    public KakaoLoginUtil() {
        kakaoUserInformation = new LinkedHashMap<>();
    }

    public SocialLoginProcessResultDto process(String code) {
        // 액세스 토큰 받아오기
        String accessToken = getAccessToken(code);

        // 유저 정보 디테일 받아오기
        getDetail(accessToken);

        kakaoUserInformation.put("authBy", "kakao");

        return new SocialLoginProcessResultDto(
            kakaoUserInformation.get("accessToken"),
            kakaoUserInformation.get("refreshToken"),
            kakaoUserInformation.get("nickname"),
            kakaoUserInformation.get("email"),
            kakaoUserInformation.get("kakaoUserId"),
            kakaoUserInformation.get("authBy")
        );
    }

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 객체 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", apiKey);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 객체에 담기 -> 만든 이유 : 아래의 exchange 함수에 HttpEntity를 넣어야 해서..
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(params, httpHeaders); // body 데이터와 headers 값을 가지고 있는 Entity

        // 카카오에게 Http 요청하기 (POST 방식) -> response라는 변수에 응답을 받음
        String response = restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class).getBody();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        String accessToken = element.getAsJsonObject().get("access_token").getAsString();
        String refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

        kakaoUserInformation.put("accessToken", accessToken);
        kakaoUserInformation.put("refreshToken", refreshToken);

        return accessToken;
    }

    public void getDetail(String accessToken) {
        // 유저정보 요청
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        String response = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            kakaoProfileRequest,
            String.class).getBody();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

        String id = element.getAsJsonObject().get("id").getAsString();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

        kakaoUserInformation.put("kakaoUserId", id);
        kakaoUserInformation.put("nickname", nickname);
        kakaoUserInformation.put("email", email);
    }
}
