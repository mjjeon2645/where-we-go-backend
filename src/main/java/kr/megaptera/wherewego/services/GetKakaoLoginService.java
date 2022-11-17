package kr.megaptera.wherewego.services;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.io.*;
import java.net.*;
import java.util.*;

@Service
@Transactional
public class GetKakaoLoginService {
    @Value("${kakao.api-key}")
    private String apiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public String accessToken(String code) {
       String accessToken = "";
       String refreshToken = "";
       String requestUrl = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + apiKey); //본인이 발급받은 key
            sb.append("&redirect_uri=" + redirectUri); // 본인이 설정한 주소
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("************");
        System.out.println(accessToken);
        System.out.println("************");
        return accessToken;
    }

    public HashMap<String, Object> userInformation(String accessToken) {
        HashMap<String, Object> userInformation = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            userInformation.put("nickname", nickname);
            userInformation.put("email", email);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInformation;
    }
}
