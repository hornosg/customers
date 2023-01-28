package com.hornosg.customers.infrastructure.rest;

import com.google.gson.Gson;
import com.hornosg.customers.application.create.NewCustomerCommand;
import com.hornosg.customers.application.create.NewCustomerHandler;
import com.hornosg.customers.infrastructure.persistence.CustomerMongoRepository;
import com.hornosg.shared.infrastructure.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping(BaseController.BASE_URI)
public class CustomersPostController extends BaseController
{
    private final CustomerMongoRepository repository = new CustomerMongoRepository();

    @PostMapping("/v1/customer")
    public ResponseEntity<Object> createCustomer(@RequestBody Map<String, Object> requestBody) throws IOException, NoSuchAlgorithmException {
        if (emptyBody(requestBody)){
            throw new IllegalArgumentException(MESSAGE_REQUEST_WITHOUT_BODY);
        };

        Gson gson = new Gson();

        NewCustomerHandler handler = new NewCustomerHandler(repository);
        handler.invoke(
            new NewCustomerCommand(
                requestBody.get("tenantId") != null ? requestBody.get("tenantId").toString() : "",
                requestBody.get("name") != null ? requestBody.get("name").toString() : "",
                requestBody.get("surname") != null ? requestBody.get("surname").toString() : "",
                requestBody.get("email") != null ? requestBody.get("email").toString() : "",
                requestBody.get("address") != null ? gson.toJson(requestBody.get("address")) : null,
                requestBody.get("phone") != null ? gson.toJson(requestBody.get("phone")) : null,
                requestBody.get("profile") != null ? requestBody.get("profile").toString() : null,
                requestBody.get("type") != null ? requestBody.get("type").toString() : null
            )
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
