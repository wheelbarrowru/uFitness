package com.example.data.service;


import com.example.data.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Class of RestClientService which connects UserDTO
 */
@Service
public class RestClientService {
    @Value("${server.port}")
    private String serverPort;

    /**
     * Autowired constructor of RestClientService
     */
    @Autowired
    public RestClientService() {
    }

    /**
     * This method fetch UserDTO to ProfileController
     * FIXME
     * @param id User's id
     * @return Objects.requireNonNull
     * @see UserDTO#UserDTO()
     */
    public UserDTO fetchUserProfile(int id) {
        final String url = "http://localhost:" + serverPort + "/profile/data/" + id;
        return Objects.requireNonNull(WebClient.create().get()
                .uri(url)
                .headers(headers -> headers.set("USER_AGENT", "admin"))
                .retrieve().toEntity(UserDTO.class).
                block()).getBody();

    }

}
