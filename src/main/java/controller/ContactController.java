package controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Contact;
import service.ContactService;

@RestController
@CrossOrigin
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping(value = "/add")
    public void addContact(@RequestBody Contact contact) throws IOException {
        contactService.addContact(contact);
    }

    @GetMapping(value = "/get/{lastName}")
    public List<Contact> getContactByName(@PathVariable String lastName){
        return contactService.getContactByName(lastName);
    }

    @GetMapping(value = "/getAll")
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteContact(@PathVariable int id) throws IOException {
        contactService.deleteContact(id);
    }
    
}


