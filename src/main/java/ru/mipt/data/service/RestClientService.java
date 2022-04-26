package ru.mipt.data.service;


import ru.mipt.data.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Class which help fetch UserDTO from ProfileController
 */
@Service
public class RestClientService {
    @Value("${server.port}")
    private String serverPort;

    /**
     * No-argument constructor of RestClientService
     */
    @Autowired
    public RestClientService() {
    }

    /**
     * This method fetch UserDTO from ProfileController using profile/data/{id}
     *
     * @param id User's id
     * @return UserDTO
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
