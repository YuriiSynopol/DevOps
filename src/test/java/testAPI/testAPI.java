package testAPI;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


public class testAPI {

    @Test
    public void getShopingGroup() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/?output_format=JSON")
                .get()
                .build();

        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
    }

    @Test
    public void checkStatusLanguages() {
        given().
                when().
                get("http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/languages/?io_format=JSON").
                then().
                assertThat().
                statusCode(200).
                and().
                body("languages.id", hasSize(2));

    }

    @Test
    public void addNewLanguages() {
        RequestSpecification request = given();

        JSONObject json = new JSONObject();

        json.put("id", "3");
        json.put("name", "French");
        json.put("iso_code", "fr");
        json.put("locale", "fr-FR");
        json.put("language_code", "fr-fr");
        json.put("active", "1");
        json.put("is_rtl", "0");
        json.put("date_format_lite", "m/d/Y");
        json.put("date_format_full", "m/d/Y H:i:s");

        request.header("Content-Type", "application/json");

        Response response = request
                .post("http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/languages");

        int code = response.statusCode();

        Assert.assertEquals(code, 201);

    }


    @Test
    public void checkLenguagesInfoEN() {
        RestAssured.baseURI = "http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/languages";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/1/?io_format=JSON");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("en"), true);
    }


    @Test
    public void TestLanguageJsonString() throws IOException {
        String path="http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/languages/1/?io_format=JSON";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,
                path);

        JsonPath jsonpath = response.jsonPath();

        jsonParser parser = new jsonParser();
        Map<String, String> actual = parser.parseResponse(
                path);

        Assert.assertEquals(jsonpath.getString("language.id"), actual.get("language.id"));
        Assert.assertEquals(jsonpath.getString("language.name"), actual.get("language.name"));
        Assert.assertEquals(jsonpath.getString("language.iso_code"), actual.get("language.iso_code"));
        Assert.assertEquals(jsonpath.getString("language.locale"), actual.get("language.locale"));
    }

    @Test
    public void TestWeatherSite() {
        Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        int code = resp.getStatusCode();
        System.out.println("Status code " + code);

        Assert.assertEquals(code, 200);

    }

    @Test
    public void TestBody() {
        Response resp = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        String data = resp.asString();
        System.out.println("Data is " + data);
        System.out.println("Time" + resp.getTime());


    }


}
