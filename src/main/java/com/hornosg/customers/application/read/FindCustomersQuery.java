package com.hornosg.customers.application.read;

public class FindCustomersQuery {
    public String tenantId;
    public int pageNumber;
    public int pageSize;

    public FindCustomersQuery(String tenantId, int pageNumber, int pageSize) {
        this.tenantId = tenantId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
