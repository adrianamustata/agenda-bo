package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import entity.Contact;

@Service
public class ContactService {

    String outputFile = "C:/Users/t/PROJECTS/file1.txt";

    public void addContact(Contact contact) throws IOException {

        boolean alreadyExists = new File(outputFile).exists();

        PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));
        if (!alreadyExists) {
            writer.write("Id:" + "1");
        } else {
            BufferedReader input = new BufferedReader(new FileReader(outputFile));
            String last = "", line;

            while ((line = input.readLine()) != null) {
                last = line;
            }
            last = last.substring(last.indexOf(":") + 1);
            last = last.substring(0, last.indexOf(","));
            int id = Integer.parseInt(last) + 1;
            writer.write("Id:" + String.valueOf(id));

            input.close();
        }
        writer.write(",");
        writer.write("FirstName:" + contact.getFirstName());
        writer.write(",");
        writer.write("LastName:" + contact.getLastName());
        writer.write(",");
        writer.write("PhoneNumber:" + contact.getPhoneNumber());
        writer.write("\n");
        writer.close();

    }

    public List<Contact> getContactByName(String name) {
        List<Contact> contactList = new ArrayList<>();

        try {
            File myObj = new File(outputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              Contact contact = new Contact();
              int id = Integer.parseInt(data.substring(data.indexOf(':') + 1, data.indexOf(',')));
              contact.setId(id);

              data = data.substring(data.indexOf(",") + 1);
              contact.setFirstName(data.substring(data.indexOf(':') + 1, data.indexOf(',')));

              data = data.substring(data.indexOf(",") + 1);
              contact.setLastName(data.substring(data.indexOf(':') + 1, data.indexOf(',')));

              data = data.substring(data.indexOf(",") + 1);
              contact.setPhoneNumber(data.substring(data.indexOf(':') + 1, data.length()));

              if (contact.getLastName().equalsIgnoreCase(name)){
                 contactList.add(contact);
              }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }

        return contactList;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();

        try {
            File myObj = new File(outputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              Contact contact = new Contact();
              int id = Integer.parseInt(data.substring(data.indexOf(':') + 1, data.indexOf(',')));
              contact.setId(id);

              data = data.substring(data.indexOf(",") + 1);
              contact.setFirstName(data.substring(data.indexOf(':') + 1, data.indexOf(',')));

              data = data.substring(data.indexOf(",") + 1);
              contact.setLastName(data.substring(data.indexOf(':') + 1, data.indexOf(',')));

              data = data.substring(data.indexOf(",") + 1);
              contact.setPhoneNumber(data.substring(data.indexOf(':') + 1, data.length()));

              contactList.add(contact);    
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }

        return contactList;
    }

    public void deleteContact(int id) throws IOException {
        File inputFile = new File(outputFile);
        File tempFile = new File("C:/Users/t/PROJECTS/myTempFile.txt");
        
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String lineToRemove = null;
        String currentLine;
        
        while((currentLine = reader.readLine()) != null) {
            int idContact = Integer.parseInt(currentLine.substring(currentLine.indexOf(':') + 1, currentLine.indexOf(',')));
            if(id == idContact){
                lineToRemove = currentLine;
            }

            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close(); 
        reader.close(); 
        Files.deleteIfExists(inputFile.toPath()); 
        tempFile.renameTo(inputFile);
    }

}