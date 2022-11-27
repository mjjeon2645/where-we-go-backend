package kr.megaptera.wherewego.utils;

import kr.megaptera.wherewego.exceptions.*;
import org.springframework.beans.factory.annotation.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class NaverBlogUtil {
    @Value("${naver.api-key}")
    private String apiKey;

    @Value("${naver.secret}")
    private String secret;

    public String search(String keyword) {
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException error) {
            throw new EncodingFailedException();
        }

        //URL 세팅
        String apiUrl = "https://openapi.naver.com/v1/search/blog?display=3&query=" + keyword;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", apiKey);
        requestHeaders.put("X-Naver-Client-Secret", secret);
        String responseBody = get(apiUrl, requestHeaders);

        System.out.println(responseBody);

        return responseBody;
    }

    public String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection connection = connect(apiUrl);

        try {
            connection.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(connection.getInputStream());
            } else { // 오류 발생
                return readBody(connection.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            connection.disconnect();
        }
    }

    public HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    public String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
