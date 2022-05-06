package ru.mipt.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.mipt.data.dto.UserDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestClientServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    RestClientService restClientService = new RestClientService();

    @Test
    void fetchUserProfile() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setServerPort(port);

        ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attrs);

        UserDTO userDTO = new UserDTO();

        Assertions.assertEquals(userDTO, restClientService.fetchUserProfile(0));
    }
}