package com.devvictor.user_crud_spring.unit.services;

import com.devvictor.user_crud_spring.dtos.person.CreatePersonDto;
import com.devvictor.user_crud_spring.dtos.person.UpdatePersonDto;
import com.devvictor.user_crud_spring.exceptions.RequiredObjectIsNullException;
import com.devvictor.user_crud_spring.models.Person;
import com.devvictor.user_crud_spring.repositories.PersonRepository;
import com.devvictor.user_crud_spring.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    private PersonMock input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    public void setUp() {
        input = new PersonMock();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Person entity = input.mockEntity();
        Person persisted = input.mockEntity();
        persisted.setId(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(new CreatePersonDto(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getGender()
        ));

        assertNotNull(result);
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getAddress(), result.getAddress());
        assertEquals(entity.getGender(), result.getGender());
    }

    @Test
    public void testCreateWithNullPerson() {
        assertThrows(RequiredObjectIsNullException.class, () ->
                service.create(null));
    }

    @Test
    public void testFindAll() {
        List<Person> entities = input.mockEntityList(10);

        when(repository.findAll()).thenReturn(entities);

        var result = service.findAll();

        assertNotNull(result);
        assertTrue(entities.size() == 10);
    }

    @Test
    public void testFindById() {
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getAddress(), result.getAddress());
        assertEquals(entity.getGender(), result.getGender());
    }

    @Test
    public void testUpdate() {
        Person entity = input.mockEntity();
        Person persisted = input.mockEntity();
        persisted.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(persisted.getId(), new UpdatePersonDto(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getGender()
        ));

        assertNotNull(result);
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getAddress(), result.getAddress());
        assertEquals(entity.getGender(), result.getGender());
    }

    @Test
    public void testUpdateWithNullPerson() {
        assertThrows(RequiredObjectIsNullException.class, () ->
                service.update(1L, null));
    }

    @Test
    public void testDelete() {
        Person entity = input.mockEntity();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}
