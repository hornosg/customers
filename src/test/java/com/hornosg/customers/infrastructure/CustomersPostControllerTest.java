package com.hornosg.customers.infrastructure;

import com.hornosg.customers.BasicRestTestUtils;
import com.hornosg.customers.domain.exceptions.CustomerNameRequiredException;
import com.hornosg.customers.domain.exceptions.CustomerSurnameRequiredException;
import com.hornosg.shared.domain.exceptions.EmailInvalidException;
import com.hornosg.shared.domain.exceptions.EmailRequiredException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import static com.hornosg.shared.infrastructure.BaseController.MESSAGE_REQUEST_WITHOUT_BODY;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomersPostControllerTest extends BasicRestTestUtils {

    public static final String POST_CUSTOMER_URI = "/v1/customer";

    @Test
    public void CreateCustomerWithEmptyBody() throws JSONException {
        Map<String, Object> requestBody = new HashMap<>();

        ResponseEntity<String> response = newRequest(POST_CUSTOMER_URI, requestBody);
        JSONObject responseJO = new JSONObject(response.getBody());
        assertEquals(response.getStatusCode().value(), responseJO.get("statusCode"));
        assertEquals(responseJO.get("statusCode"), HttpStatus.BAD_REQUEST.value());
        assertEquals(responseJO.get("message"), MESSAGE_REQUEST_WITHOUT_BODY);
    }

    @Test
    public void CreateCustomerWithoutName() throws JSONException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("surname", super.faker.name().lastName());

        ResponseEntity<String> response = newRequest(POST_CUSTOMER_URI, requestBody);

        JSONObject responseJO = new JSONObject(response.getBody());
        assertEquals(response.getStatusCode().value(), responseJO.get("statusCode"));
        assertEquals(responseJO.get("statusCode"), HttpStatus.BAD_REQUEST.value());
        assertEquals(
            responseJO.get("message"),
        CustomerNameRequiredException.ERROR_MESSAGE + ", " +
            EmailRequiredException.ERROR_MESSAGE
        );
    }

    @Test
    public void CreateCustomerWithoutSurname() throws JSONException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", super.faker.name().firstName());

        ResponseEntity<String> response = newRequest(POST_CUSTOMER_URI, requestBody);

        JSONObject responseJO = new JSONObject(response.getBody());
        assertEquals(response.getStatusCode().value(), responseJO.get("statusCode"));
        assertEquals(responseJO.get("statusCode"), HttpStatus.BAD_REQUEST.value());
        assertEquals(
            responseJO.get("message"),
        CustomerSurnameRequiredException.ERROR_MESSAGE + ", " +
            EmailRequiredException.ERROR_MESSAGE
        );
    }

    @Test
    public void CreateCustomerWithoutEmail() throws JSONException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", super.faker.name().firstName());
        requestBody.put("surname", super.faker.name().lastName());

        ResponseEntity<String> response = newRequest(POST_CUSTOMER_URI, requestBody);

        JSONObject responseJO = new JSONObject(response.getBody());
        assertEquals(response.getStatusCode().value(), responseJO.get("statusCode"));
        assertEquals(responseJO.get("statusCode"), HttpStatus.BAD_REQUEST.value());
        assertEquals(responseJO.get("message"), EmailRequiredException.ERROR_MESSAGE);
    }


    @Test
    public void CreateCustomerWithInvalidEmail() throws JSONException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", super.faker.name().firstName());
        requestBody.put("surname", super.faker.name().lastName());
        requestBody.put("email", "notisemailvalid.com");
        Map<String, Object> address = new HashMap<>();
        address.put("street", super.faker.address().streetName());
        address.put("number", super.faker.address().streetAddressNumber());
        requestBody.put("address", address);

        ResponseEntity<String> response = newRequest(POST_CUSTOMER_URI, requestBody);

        JSONObject responseJO = new JSONObject(response.getBody());
        assertEquals(response.getStatusCode().value(), responseJO.get("statusCode"));
        assertEquals(responseJO.get("statusCode"), HttpStatus.BAD_REQUEST.value());
        assertEquals(responseJO.get("message"), EmailInvalidException.ERROR_MESSAGE);
    }
}