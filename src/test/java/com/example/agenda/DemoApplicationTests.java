package com.example.agenda;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import controller.ContactController;
import entity.Contact;

@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private ContactController contactController;

	@Test
	public void contextLoads() {
		System.out.println("Acum aici");
	}

	@Test
	public void testAddContact() throws IOException {
		Contact contact = new Contact(1, "Ioana", "Mihai", "0765892354");
		contactController.addContact(contact);
		List<Contact> contacts = contactController.getContactByName(contact.getLastName());
		assertEquals(1, contacts.size());
		assertEquals(contact.getFirstName(), contacts.get(0).getFirstName());
		assertEquals(contact.getLastName(), contacts.get(0).getLastName());
		assertEquals(contact.getPhoneNumber(), contacts.get(0).getPhoneNumber());
		assertTrue(contacts.get(0).getId() != 0);
	}

	@Test
	public void testIncompleteContactAdd() throws IOException {
		Contact contact = new Contact();
		contact.setLastName("Ion");
		contactController.addContact(contact);
		List<Contact> contacts = contactController.getAllContacts();
		assertEquals(4, contacts.size());
	}
}
