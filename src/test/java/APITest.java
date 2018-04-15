
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static io.restassured.RestAssured.when;

public class APITest extends BaseTest{

    protected final String LUKE_URL = BASE_URL + "people/1/";


    @Test
    public void verifyAPI() {
        //get Luke planet URL//
        String lukePlanet =
                given()
                        .relaxedHTTPSValidation()
                        .when()
                        .get(LUKE_URL)
                        .then()
                        .statusCode(200)
                        .extract().path("homeworld");

        Response lukePlanetResponse =
                when()
                        .get(lukePlanet)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Response filmResponse =
                when()
                            .get((String) getValue(lukePlanetResponse, "films").get(0))
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

        Assert.assertEquals(lukePlanetResponse.path("name"), "Tatooine", "Check if planet name is correct");
        Assert.assertEquals(lukePlanetResponse.path("population"), "200000", "Check if population is correct");
        Assert.assertEquals(filmResponse.path("title"), "Attack of the Clones", "Check if file title is correct");
        Assert.assertTrue(getValue(filmResponse, "characters").contains(LUKE_URL), "Check if character is exist in film");
        Assert.assertTrue(getValue(filmResponse, "planets").contains(getValue(lukePlanetResponse, "films").get(0)), "Check if planet is exist in film");




       //response.path("homeworld");




       //



    }
}
