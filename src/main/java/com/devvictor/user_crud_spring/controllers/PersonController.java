package com.devvictor.user_crud_spring.controllers;

import com.devvictor.user_crud_spring.dtos.person.CreatePersonDto;
import com.devvictor.user_crud_spring.dtos.person.PersonResponseDto;
import com.devvictor.user_crud_spring.dtos.person.UpdatePersonDto;
import com.devvictor.user_crud_spring.presenters.PersonPresenter;
import com.devvictor.user_crud_spring.services.PersonService;
import com.devvictor.user_crud_spring.utils.AppMediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "Person", description = "Endpoints of person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML }, produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    @Operation(summary = "Creates a person", description = "Creates a person",
            tags = "Person",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonResponseDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content ),
            }
    )
    public ResponseEntity<PersonResponseDto> create(@RequestBody CreatePersonDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PersonPresenter.toResponseObject(personService.create(dto)));
    }

    @GetMapping(produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    @Operation(summary = "Finds persons", description = "Finds persons",
            tags = "Person",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = PersonResponseDto.class
                                    )))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content ),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content ),
            }
    )
    public List<PersonResponseDto> findAll() {
        return PersonPresenter.toResponseList(personService.findAll());
    }

    @GetMapping(value = "/{id}", produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    @Operation(summary = "Finds a person", description = "Finds a person",
            tags = "Person",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonResponseDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content ),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content ),
            }
    )
    public PersonResponseDto findById(@PathVariable(name = "id") Long id) {
        return PersonPresenter.toResponseObject(personService.findById(id));
    }

    @PutMapping(value = "/{id}", consumes = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML }, produces = { AppMediaType.JSON, AppMediaType.XML, AppMediaType.YAML })
    @Operation(summary = "Updates a person", description = "Updates a person",
            tags = "Person",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonResponseDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content ),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content ),
            }
    )
    public PersonResponseDto update(@PathVariable(name = "id") Long id,
                                    @RequestBody UpdatePersonDto dto) {
        return PersonPresenter.toResponseObject(personService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a person", description = "Deletes a person",
            tags = "Person",
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content ),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content ),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
