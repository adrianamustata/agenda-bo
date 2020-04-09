package com.example.agenda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import entity.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import controller.ContactController;

@SpringBootTest
public class TestNGTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private ContactController contactController;

    private Contact contact;

    @BeforeClass
    public void initialize() {
        contact = new Contact();
        contact.setFirstName("Cristian");
        contact.setLastName("Vasile");
        contact.setPhoneNumber("0734267018");
    }

    @Test
    public void testGetAllContacts() {
        List<Contact> contactsList = contactController.getAllContacts();
        assertEquals(3, contactsList.size(), "Recieved is not 3");
    }

    @Test
    public void testAddContact() throws IOException {
        contactController.addContact(contact);
        List<Contact> contacts = contactController.getContactByName(contact.getLastName());
        assertEquals(contact.getLastName(), contacts.get(0).getLastName());
    }

    @Test
    public void testDeleteContact() throws IOException {
        List<Contact> contactsList = contactController.getAllContacts();
        contactController.deleteContact(contactsList.get(contactsList.size()-1).getId());
        List<Contact> contacts = contactController.getContactByName(contact.getLastName());
        assertEquals(1, contacts.size(), "Recieved is not 1");
    }

    @Test
    @Parameters("lastName")
    public void testGetContactByName(String lastName) {
        List<Contact> contacts = contactController.getContactByName(lastName);
        assertEquals(1, contacts.size(), "Recieved is not 1");
    }


}