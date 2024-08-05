package com.devvictor.user_crud_spring.unit.services;

import com.devvictor.user_crud_spring.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMock {
    public Person mockEntity() {
        var entity = new Person();
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setAddress("address");
        entity.setGender("gender");
        return entity;
    }

    public List<Person> mockEntityList(int length) {
        ArrayList<Person> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            var entity = new Person();
            int id = length + 1;
            entity.setId(id);
            entity.setFirstName("firstName " + id);
            entity.setLastName("lastName " + id);
            entity.setAddress("address " + id);
            entity.setGender("gender " + id);

            list.add(entity);
        }

        return list;
    }
}
