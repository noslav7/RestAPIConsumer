import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Введите предложение на русском языке");
        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();

        RestTemplate restTemplate = new RestTemplate();

        String url =  "https://translate.api.cloud.yandex.net/translate/v2/translate";

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        httpHeaders.add("Authorisation", "Bearer" + "<Yandex TOKEN>");

        Map<String, String> jsonData = new HashMap<>();

        jsonData.put("folderId", "<идентификатор каталога>");
        jsonData.put("targetLanguageCode", "en");
        jsonData.put("texts", "[" + sentenceToTranslate + "]");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, httpHeaders);

        YandexResponse response = restTemplate.postForObject(url, request, YandexResponse.class);

//        System.out.println(response);

//        парсим поученный JSON с помощью Jackson
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode obj = objectMapper.readTree(response);

        System.out.println("Перевод " + response.getTranslations().get(0).getText());
    }
}
