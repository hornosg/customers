package com.hornosg.customers;

import net.datafaker.Faker;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicTestUtils {
    protected Faker faker = new Faker();
}
