package ru.javamentor.springBootRestTemplate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javamentor.springBootRestTemplate.model.User;

import static org.springframework.http.HttpMethod.*;

@Service
public class RestTemplateServiceImpl {

    private final static String URL = "http://91.241.64.178:7081/api/users";
    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private String result = "";
    private String cookieHeader;

    public void getUsers(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        HttpHeaders responseEntityHeaders = responseEntity.getHeaders();
        cookieHeader = responseEntityHeaders.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(cookieHeader);
    }

    public void addUser(User user) {
        httpHeaders.add("Cookie", cookieHeader);
        HttpEntity<User> request  = new HttpEntity(user, httpHeaders);
        result += restTemplate.exchange(URL, POST, request, String.class).getBody();
        System.out.println(result);
    }

    public void updateUser(User user) {
        HttpEntity<User> request  = new HttpEntity(user, httpHeaders);
        result += restTemplate.exchange(URL, PUT, request, String.class).getBody();
        System.out.println(result);
    }

    public void deleteUser(long id) {
        HttpEntity<User> request  = new HttpEntity(httpHeaders);
        result += restTemplate.exchange(URL + "/" + id, DELETE, request, String.class).getBody();
        System.out.println(result);
    }
}
