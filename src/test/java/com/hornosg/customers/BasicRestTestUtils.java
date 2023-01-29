package com.hornosg.customers;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicRestTestUtils  extends BasicTestUtils
{
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    protected Faker faker = new Faker();

    @LocalServerPort
    private int serverPort;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + serverPort + "/hornosg/" + uri;
    }

    @Test
    public void validateRandomPort(){
        assertTrue(serverPort > 0);
    }

    public ResponseEntity<String> newRequest(
            String uri,
            Map<String, Object> requestBody
    ) {
        return restTemplate.exchange(
                        createURLWithPort(uri),
                        HttpMethod.POST,
                        new HttpEntity<Map<String, Object>>(requestBody, headers),
                        String.class
                );
    }
}
