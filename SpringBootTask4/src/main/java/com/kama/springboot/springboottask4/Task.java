package com.kama.springboot.springboottask4;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Task {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";
        String urlDelete = "http://94.198.50.185:7081/api/users/3";

        // Получение куки
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null, String.class);
        List<String> cookies = response.getHeaders().get("set-cookie");
        String sessionId = cookies.get(0);
        System.out.println(sessionId);

        // Создание и добавление нового пользователя
        User user = new User(3L,"James","Brown",(byte)12);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String part1 = response2.getBody();
        System.out.println(part1);

        // Обновление пользоавтеля
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entity2 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response3 = restTemplate.exchange(url, HttpMethod.PUT, entity2, String.class);
        String part2 = response3.getBody();
        System.out.println(part2);

        // Удаление пользователя
        ResponseEntity<String> response4 = restTemplate.exchange(urlDelete, HttpMethod.DELETE, entity2, String.class);
        String part3 = response4.getBody();
        System.out.println(part3);

        // Итог
        String result = part1+part2+part3;
        int resultLength = result.length();
        System.out.println(result + " " + resultLength);
    }
}
