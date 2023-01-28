package com.hornosg.customers.infrastructure.rest;

import com.hornosg.customers.application.delete.DeleteCustomerCommand;
import com.hornosg.customers.application.delete.DeleteCustomerHandler;
import com.hornosg.customers.infrastructure.persistence.CustomerMongoRepository;
import com.hornosg.shared.infrastructure.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(BaseController.BASE_URI)
public class CustomersDeleteController extends BaseController
{
    private final CustomerMongoRepository repository = new CustomerMongoRepository();

    @DeleteMapping("/v1/customer/{id}")
    public ResponseEntity<Map> deleteCustomer(@PathVariable String id){
        DeleteCustomerHandler handler = new DeleteCustomerHandler(repository);
        handler.invoke(new DeleteCustomerCommand(id));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
