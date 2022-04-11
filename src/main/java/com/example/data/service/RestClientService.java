package com.example.data.service;

import com.example.data.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class RestClientService {
    @Value("${server.port}")
    private String serverPort;

    public UserDTO fetchUserProfile(int id) {
        System.out.println(serverPort);
        final String url = String.format("http://localhost:" + serverPort + "/profile/data/" + id);
        System.out.println(url);
        final RequestHeadersSpec<?> spec = WebClient.create().get().uri(url);

        UserDTO response = spec.retrieve().toEntity(UserDTO.class).block().getBody();
        System.out.println("response: "+response);
        return response;

    }

}
