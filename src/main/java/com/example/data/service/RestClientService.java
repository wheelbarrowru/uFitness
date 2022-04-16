package com.example.data.service;


import com.example.data.dto.UserDTO;
import com.example.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class RestClientService {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public RestClientService(UserRepository userRepository) {
    }

    public UserDTO fetchUserProfile(int id) {
        final String url = "http://localhost:" + serverPort + "/profile/data/" + id;
        System.out.println(url);
        return Objects.requireNonNull(WebClient.create().get()
                .uri(url)
                .headers(headers -> headers.set("USER_AGENT", "admin"))
                .retrieve().toEntity(UserDTO.class).
                block()).getBody();

    }

}
