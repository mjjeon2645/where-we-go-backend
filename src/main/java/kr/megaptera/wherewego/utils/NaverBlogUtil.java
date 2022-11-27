package kr.megaptera.wherewego.utils;

import com.google.gson.*;
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

    public List<Map<String, String>> search(String keyword) {
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

        JsonElement element = JsonParser.parseString(responseBody);

        JsonArray items = element.getAsJsonObject().get("items").getAsJsonArray();

        List<Map<String, String>> naverBlogs = new ArrayList<>();

        for (int i = 0; i < items.size(); i += 1) {
            JsonElement jsonElement = items.get(i);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String title = jsonObject.get("title").getAsString().replace("<b>", "").replace("</b>", "");
            String blogLink = jsonObject.get("link").getAsString();
            String postDate = jsonObject.get("postdate").getAsString();
            String blogger = jsonObject.get("bloggername").getAsString();

            Map<String, String> map = new LinkedHashMap<>();

            map.put("title", title);
            map.put("blogLink", blogLink);
            map.put("postDate", postDate);
            map.put("blogger", blogger);

            naverBlogs.add(map);
        }

        return naverBlogs;
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
        } catch (IOException error) {
            throw new ApiRequestFailedException();
        } finally {
            connection.disconnect();
        }
    }

    public HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException error) {
            throw new WrongApiUrlException();
        } catch (IOException error) {
            throw new ConnectionFailedException();
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
        } catch (IOException error) {
            throw new ApiResponseFailException();
        }
    }
}
