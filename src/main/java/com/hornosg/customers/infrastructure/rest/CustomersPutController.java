package com.hornosg.customers.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.hornosg.customers.application.update.UpdateCustomerCommand;
import com.hornosg.customers.application.update.UpdateCustomerHandler;
import com.hornosg.customers.infrastructure.persistence.CustomerMongoRepository;
import com.hornosg.shared.infrastructure.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping(BaseController.BASE_URI)
public class CustomersPutController extends BaseController
{
    private final CustomerMongoRepository repository = new CustomerMongoRepository();

    @PutMapping("/v1/customer/{id}")
    public ResponseEntity<Object> updateCustomer(
            @PathVariable String id,
            @RequestBody Map<String, Object> requestBody
    ) throws NoSuchAlgorithmException, JsonProcessingException {

        if (emptyBody(requestBody)){
            throw new IllegalArgumentException(MESSAGE_REQUEST_WITHOUT_BODY);
        };

        Gson gson = new Gson();

        UpdateCustomerHandler handler = new UpdateCustomerHandler(repository);
        handler.invoke(
            new UpdateCustomerCommand(
                id,
                requestBody.get("name") != null ? requestBody.get("name").toString() : null,
                requestBody.get("surname") != null ? requestBody.get("surname").toString() : null,
                requestBody.get("email") != null ? requestBody.get("email").toString() : null,
                requestBody.get("address") != null ? gson.toJson(requestBody.get("address")) : null,
                requestBody.get("phone") != null ? gson.toJson(requestBody.get("phone")) : null,
                requestBody.get("profile") != null ? requestBody.get("profile").toString() : null,
                requestBody.get("type") != null ? requestBody.get("type").toString() : null
            )
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
