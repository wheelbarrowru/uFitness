package ru.mipt.data.controller;

import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {
    @Autowired
    private ProfileController profileController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @InjectMocks
    private UserService userService = new UserService(null){
        @Override
        public UserDTO getDTO(int id) {
            return new UserDTO(id, "username", "test",
                    "test", "test@mail.ru", "password");
        }
    };

    @Test
    void getProfileTest() {
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "password");
        profileController.setUserService(userService);
        assertNull(restTemplate
                .getForObject("http://localhost:" + port + "/profile/data/0", UserDTO.class));

        final HttpHeaders headers = new HttpHeaders();
        headers.set("USER_AGENT", "admin");

        final HttpEntity<UserDTO> entity = new HttpEntity<>(headers);

        Assertions.assertEquals(userDTO, restTemplate
                .exchange("http://localhost:" + port + "/profile/data/0", HttpMethod.GET,
                        entity, UserDTO.class).getBody());
    }
}