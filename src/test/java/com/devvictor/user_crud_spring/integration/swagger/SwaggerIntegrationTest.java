package com.devvictor.user_crud_spring.integration.swagger;

import com.devvictor.user_crud_spring.config.TestConfigs;
import com.devvictor.user_crud_spring.integration.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        String html = given().basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();
        assertTrue(html.contains("Swagger UI"));
    }
}
