package com.example.agenda;

import org.junit.Test;

import entity.Contact;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

// @SpringBootTest
public class RestAssuredTest {

    @Test
    public void testGetAllContacts() {
        RestAssured.baseURI = "http://localhost:8080/contact/getAll";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET);

        // int statusCode = response.getStatusCode();
        // assertEquals(200, statusCode);

        List<Contact> contacts = response.jsonPath().getList("$");
        assertEquals(0, contacts.size());
    }

}