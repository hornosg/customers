package com.hornosg.customers.infrastructure.rest;

import com.hornosg.customers.application.read.FindCustomerQuery;
import com.hornosg.customers.application.read.FindCustomerHandler;
import com.hornosg.customers.application.read.FindCustomersHandler;
import com.hornosg.customers.application.read.FindCustomersQuery;
import com.hornosg.customers.infrastructure.persistence.CustomerMongoRepository;
import com.hornosg.shared.infrastructure.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(BaseController.BASE_URI)
public class CustomersGetController extends BaseController
{
    private final CustomerMongoRepository repository = new CustomerMongoRepository();

    @GetMapping("/v1/customer/{id}")
    public ResponseEntity<Map> findCustomer(@PathVariable String id){
        FindCustomerHandler handler = new FindCustomerHandler(repository);
        Map<String, Object> response = handler.invoke(new FindCustomerQuery(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1/customer/list/{tenantid}/{pageNumber}/{pageSize}")
    public ResponseEntity<LinkedHashMap<String, Object>> findCustomers(
            @PathVariable String tenantid,
            @PathVariable int pageNumber,
            @PathVariable int pageSize
    ){
        FindCustomersHandler handler = new FindCustomersHandler(repository);

        LinkedHashMap<String, Object> response = handler.invoke(
                new FindCustomersQuery(tenantid, pageNumber, pageSize)
            );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
