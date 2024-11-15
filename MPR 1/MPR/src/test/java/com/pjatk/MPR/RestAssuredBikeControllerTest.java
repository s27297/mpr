package com.pjatk.MPR;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredBikeControllerTest {

    private static final String URI = "http://localhost:8080";

    @Test
    public void testGetBikeByColor() {
        when()
                .get(URI + "bike/{color}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(""))
                .body("color", equalTo(""))
                .log()
                .body();
    }

    @Test
    public void testGetBikeByName() {
        Bike bike = when()
                .get(URI + "bike/black}")
                .then()
                .statusCode(200)
                .extract()
                .as(Bike.class);

            assertEquals("ad", bike.getName());
            assertEquals("black", bike.getColor());


    }

    @Test
    public void testPostUser() {
        with()
                .body(new Bike("Artem", "Black"))
                .contentType("application/json")
        .when()
                .post(URI + "/bike/add")
                .then()
                .statusCode(200)
                .body("name", equalTo("Artem"))
                .body("color", equalTo("Black"))
                .log()
                .body();
    }
}
