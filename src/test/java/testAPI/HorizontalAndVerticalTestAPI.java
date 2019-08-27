package testAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class HorizontalAndVerticalTestAPI {

    @Test
    void checkStatusCode() {
//        Specify base URL
        RestAssured.baseURI = "http://EMG99PT2G8BL6YDF287ARWFFKKQPD64T@localhost/presta";

//        Request object
        RequestSpecification httpRequest = RestAssured.given();

//        Response object
        Response response = httpRequest.request(Method.GET, "/en/4-men");

//        Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        assertEquals(statusCode, 200);

//        Status line verification
        String statusLine = response.getStatusLine();
        System.out.println("Status line is: " + statusLine);
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkJsonPathForShops() {
//        Specify base URL
        RestAssured.baseURI = "http://EMG99PT2G8BL6YDF287ARWFFKKQPD64T@localhost/presta";

//        Request object
        RequestSpecification httpRequest = RestAssured.given();

//        Response object
        Response response = httpRequest.request(Method.GET, "/api/shops/1/?io_format=JSON");
        JsonPath jsonpath = response.jsonPath();

        Assert.assertEquals("1", jsonpath.getString("shop.id"));
        Assert.assertEquals("1", jsonpath.getString("shop.id_shop_group"));
        Assert.assertEquals("2", jsonpath.getString("shop.id_category"));
        Assert.assertEquals("1", jsonpath.getString("shop.active"));
        Assert.assertEquals("MyPresta", jsonpath.getString("shop.name"));
        Assert.assertEquals("classic", jsonpath.getString("shop.theme_name"));
    }

    @Test
    void checkJsonPathForCountries() {

//        Specify base URL
        RestAssured.baseURI = "http://EMG99PT2G8BL6YDF287ARWFFKKQPD64T@localhost/presta";
//
//        Request object
        RequestSpecification httpRequest = RestAssured.given();

//        Response object
        Response response = httpRequest.request(Method.GET, "/api/countries/1/?io_format=JSON");
        JsonPath jsonpath = response.jsonPath();

        Assert.assertEquals("1", jsonpath.getString("country.id"));
        Assert.assertEquals("1", jsonpath.getString("country.id_zone"));
        Assert.assertEquals("49", jsonpath.getString("country.call_prefix"));
        Assert.assertEquals("DE", jsonpath.getString("country.iso_code"));
        Assert.assertEquals("NNNNN", jsonpath.getString("country.zip_code_format"));
        Assert.assertEquals("[Germany, Germany]", jsonpath.getString("country.name.value"));
    }
}


