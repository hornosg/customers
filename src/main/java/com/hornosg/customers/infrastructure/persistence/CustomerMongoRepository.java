package com.hornosg.customers.infrastructure.persistence;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerTenantId;
import com.hornosg.shared.infrastructure.MongoRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class CustomerMongoRepository extends MongoRepository implements CustomerRepository {
    public static final String COLLECTION_NAME = "customers";
    private static final CustomerMongoSerialization serialization = new CustomerMongoSerialization();
    private static String database_name;

    @Value("${project-properties.database_name}")
    public void setDatabaseName(String databaseName) {
        database_name = databaseName;
    }

    @Override
    public void save(Customer customer) {
        MongoClient mongoClient = getClient();
        mongoClient.startSession();
        MongoCollection<Document> collection = getCollection(mongoClient, database_name, COLLECTION_NAME);

        InsertOneResult result = collection.insertOne(serialization.customerToDocument(customer));
        mongoClient.close();
    }

    @Override
    public Customer find(CustomerId id) {
        MongoClient mongoClient = getClient();
        mongoClient.startSession();
        MongoCollection<Document> collection = getCollection(mongoClient, database_name, COLLECTION_NAME);

        Document document = collection.find(eq("_id", id.getValue()))
            .projection(serialization.getCustomerProjections())
            .first();

        if (document == null) {
            System.out.println("No results found.");
            return null;
        }
        mongoClient.close();

        return serialization.documentToCustomer(document);
    }

    @Override
    public LinkedHashMap<String, Object> list(CustomerTenantId tenantId, int pageNumber, int pageSize) {
        MongoClient mongoClient = getClient();
        mongoClient.startSession();
        MongoCollection<Document> collection = getCollection(mongoClient, database_name, COLLECTION_NAME);

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<Customer> customers = new ArrayList<>();

        Long totalRows = collection.countDocuments(eq("tenantId", tenantId.getValue()));
        if (totalRows > 0){
            MongoCursor<Document> documentMongoCursor = collection.find(eq("tenantId", tenantId.getValue()))
                    .projection(serialization.getCustomerProjections())
                    .skip( pageNumber > 0 ? ( ( pageNumber - 1 ) * pageSize ) : 0 )
                    .limit( pageSize )
                    .sort(Sorts.descending("name")).iterator();


            try {
                while(documentMongoCursor.hasNext()) {
                    customers.add(serialization.documentToCustomer(documentMongoCursor.next()));
                }
            } finally {
                documentMongoCursor.close();
            }
        }

        result.put("totalRows", totalRows);
        result.put("data", customers);

        return result;
    }

    @Override
    public void delete(CustomerId id) {
        MongoClient mongoClient = getClient();
        mongoClient.startSession();
        MongoCollection<Document> collection = getCollection(mongoClient, database_name, COLLECTION_NAME);

        collection.deleteOne(new Document("_id", id.getValue()));
        mongoClient.close();
    }

    @Override
    public void update(Customer customer) {
        MongoClient mongoClient = getClient();
        mongoClient.startSession();
        MongoCollection<Document> collection = getCollection(mongoClient, database_name, COLLECTION_NAME);

        Document query = new Document().append("_id", customer.getId().getValue());
        Bson updates = serialization.updateCustomer(customer);
        UpdateResult result = collection.updateOne(query, updates);

        mongoClient.close();
    }
}
