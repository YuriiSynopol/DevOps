package testAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpResponse;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class jsonParser {
//    {
//        "language": {
//        "id": 2,
//                "name": "Українська (Ukrainian)",
//                "iso_code": "uk",
//                "locale": "uk-UA",
//                "language_code": "uk",
//                "active": "1",
//                "is_rtl": "0",
//                "date_format_lite": "Y-m-d",
//                "date_format_full": "Y-m-d H:i:s"
//    }
//    }

    String language;
    String id;
    String name;
    String iso_code;
    String locale;
    String language_code;
    String active;
    String is_rtl;
    String date_format_lite;
    String date_format_full;

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setIs_rtl(String is_rtl) {
        this.is_rtl = is_rtl;
    }

    public void setDate_format_lite(String date_format_lite) {
        this.date_format_lite = date_format_lite;
    }

    public void setDate_format_full(String date_format_full) {
        this.date_format_full = date_format_full;
    }


    public String getLanguage() {
        return language;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso_code() {
        return iso_code;
    }

    public String getLocale() {
        return locale;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public String getActive() {
        return active;
    }

    public String getIs_rtl() {
        return is_rtl;
    }

    public String getDate_format_lite() {
        return date_format_lite;
    }

    public String getDate_format_full() {
        return date_format_full;
    }

    public Map<String, String> parseResponse(String path) throws IOException {
        RestAssured.baseURI = path;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, path);

        JsonPath jsonpath = response.jsonPath();

        setLanguage(jsonpath.getString("language"));
        setId(jsonpath.getString("language.id"));
        setName(jsonpath.getString("language.name"));
        setIso_code(jsonpath.getString("language.iso_code"));
        setLocale(jsonpath.getString("language.locale"));
        setLanguage_code(jsonpath.getString("language.language_code"));
        setActive(jsonpath.getString("language.active"));
        setIs_rtl(jsonpath.getString("language.is_rtl"));
        setDate_format_lite(jsonpath.getString("language.date_format_lite"));
        setDate_format_full(jsonpath.getString("language.date_format_full"));

        Map<String, String> states = new HashMap<String, String>();
        states.put("language.id", getId());
        states.put("language.name", getName());
        states.put("language.iso_code", getIso_code());
        states.put("language.locale", getLocale());
        states.put("language.language_code", getLanguage_code());
        states.put("language.active", getActive());
        states.put("language.is_rt", getIs_rtl());
        states.put("language.date_format_lite", getDate_format_lite());
        states.put("language.date_format_full", getDate_format_full());

        //System.out.println(getLanguage());
//        System.out.println("language.id " + getId());
//        System.out.println("language.name " + getName());
//        System.out.println("language.iso_code " + getIso_code());
//        System.out.println("language.locale " + getLocale());
//        System.out.println("language.language_code " + getLanguage_code());
//        System.out.println("language.active" + getActive());
//        System.out.println("language.is_rtl " + getIs_rtl());
//        System.out.println("language.date_format_lite " + getDate_format_lite());
//        System.out.println("language.date_format_full " + getDate_format_full());

        return states;
    }

    public static void main(String[] args) throws IOException {
        jsonParser parser = new jsonParser();
        System.out.println(parser.parseResponse("http://KWW8SVJ68F72JYU2JHS9ELTL6BNI2K98@my-prestashop/api/languages/1/?io_format=JSON")) ;
    }


}
