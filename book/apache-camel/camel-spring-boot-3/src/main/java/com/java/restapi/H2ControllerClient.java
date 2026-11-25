package com.java.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.restapi.model.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class H2ControllerClient {

    private final CamelHttpClient http;

    public H2ControllerClient(CamelHttpClient http) {
        this.http = http;
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return http.postJson("/h2/apis/v1/employees", employee, EmployeeEntity.class, null, null);
    }

    public List<EmployeeEntity> saveEmployeeEntityList(List<EmployeeEntity> employees) {
        return http.postJson("/h2/apis/v2/employees", employees, new TypeReference<List<EmployeeEntity>>() {}, null, null);
    }

    public List<EmployeeEntity> findAllEmployeeEntities() {
        return http.get("/h2/apis/v1/employees", new TypeReference<List<EmployeeEntity>>() {}, null, null);
    }

}
