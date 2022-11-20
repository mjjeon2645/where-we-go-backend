package kr.megaptera.wherewego.infrastructure;

import com.google.gson.*;
import kr.megaptera.wherewego.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

import java.util.*;

@Service
@Transactional
public class NaverLoginService {
    @Value("${naver.api-key}")
    private String apiKey;

    @Value("${naver.redirect-uri}")
    private String redirectUri;

    @Value("${naver.secret}")
    private String secret;

    @Value("${naver.state-string}")
    private String state;

    private Map<String, String> naverUserInformation;

    public NaverLoginService() {
        naverUserInformation = new LinkedHashMap<>();
    }

    public LoginResultDto naverLogin(String code) {
        String accessToken = getAccessToken(code);
        getDetail(accessToken);

        return new LoginResultDto(accessToken, naverUserInformation.get("nickName"));
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
        params.add("client_secret", secret);
        params.add("code", code);
        params.add("state", state);
        params.add("redirect_uri", redirectUri);

        // HttpHeader와 HttpBody를 하나의 객체에 담기 -> 만든 이유 : 아래의 exchange 함수에 HttpEntity를 넣어야 해서..
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest =
            new HttpEntity<>(params, httpHeaders); // body 데이터와 headers 값을 가지고 있는 Entity

        // 네이버에게 Http 요청하기 (POST 방식) -> response라는 변수에 응답을 받음
        String response = restTemplate.exchange(
            "https://nid.naver.com/oauth2.0/token",
            HttpMethod.POST,
            naverTokenRequest,
            String.class).getBody();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        String accessToken = element.getAsJsonObject().get("access_token").getAsString();
        String refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
        String expiresIn = element.getAsJsonObject().get("expires_in").getAsString();

        naverUserInformation.put("accessToken", accessToken);
        naverUserInformation.put("refreshToken", refreshToken);
        naverUserInformation.put("expires_in", expiresIn);

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
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);

        String response = restTemplate.exchange(
            "https://openapi.naver.com/v1/nid/me",
            HttpMethod.GET,
            naverProfileRequest,
            String.class).getBody();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);

        JsonObject naverAccount = element.getAsJsonObject().get("response").getAsJsonObject();

        String id = naverAccount.getAsJsonObject().get("id").getAsString();
        String nickname = naverAccount.getAsJsonObject().get("nickname").getAsString();
        String email = naverAccount.getAsJsonObject().get("email").getAsString();

        naverUserInformation.put("naverUserId", id);
        naverUserInformation.put("nickName", nickname);
        naverUserInformation.put("email", email);
    }
}
