package com.bridgelabz;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {

    //arraylist  and hashmap implementation
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<ContactOfPerson> contactList ;
    public HashMap<String, ArrayList<ContactOfPerson>> personByState;
    public HashMap<String, ArrayList<ContactOfPerson>> personByCity;

    public AddressBook() {
        personByCity = new HashMap<String, ArrayList<ContactOfPerson>>();
        personByState = new HashMap<String, ArrayList<ContactOfPerson>>();
        contactList = new ArrayList<>();
    }

    public ArrayList<ContactOfPerson> addContactDetails(){
        System.out.println("Enter the Details of ContactDetails");
        System.out.println("Enter the first name");
        String firstName = sc.next();
        if (checkDuplicate(firstName)) {
            System.out.println("Person is already exist");
        }else {
            System.out.println("Enter the Last name");
            String lastName = sc.next();
            System.out.println("Enter the Address");
            String address = sc.next();
            System.out.println("Enter the City");
            String city = sc.next();
            System.out.println("Enter the State");
            String state = sc.next();
            System.out.println("Enter the email");
            String email = sc.next();
            System.out.println("Enter the ZipCode");
            String zip = sc.next();
            System.out.println("Enter the contact number...");
            String phoneNumber = sc.next();
            ContactOfPerson contactofPerson = new ContactOfPerson(firstName, lastName, address, city, state, email, phoneNumber, zip);
            contactList.add(contactofPerson);
            if(!personByState.containsKey(state)){
                personByState.put(state,new ArrayList<ContactOfPerson>());
            }
            personByState.get(state).add(contactofPerson);

            if(!personByCity.containsKey(city)){
                personByCity.put(city,new ArrayList<ContactOfPerson>());
            }
            personByCity.get(city).add(contactofPerson);



        }return contactList;}

    public boolean editContactDetails(String Name) {
        int flag = 0;
        for(ContactOfPerson contact: contactList)
        {
            if(contact.getFirstName().equals(Name))
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Address: ");
                String address = sc.next();
                contact.setAddress(address);
                System.out.println("Enter City: ");
                String city = sc.next();
                contact.setCity(city);
                System.out.println("Enter State: ");
                String state = sc.next();
                contact.setState(state);
                System.out.println("Enter Email: ");
                String email = sc.next();
                contact.setEmail(email);
                System.out.println("Enter Phone Number:");
                String phoneNumber = sc.next();
                contact.setPhoneNumber(phoneNumber);
                System.out.println("Enter Zip Code: ");
                String zip = sc.next();
                contact.setZip(zip);
                flag = 1;
            }
        }
        return flag == 1;
    }
    public boolean deleteContact(String name) {
        int flag = 0;
        for(ContactOfPerson contact: contactList)
        {
            if(contact.getFirstName().equals(name))
            {
                contactList.remove(contact);
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }
    public boolean checkDuplicate(String fname)
    {
        int flag=0;
        for (ContactOfPerson p: contactList)
        {
            if (p.getFirstName().equals(fname))
            {
                flag=1;
                break;
            }
        }
        return flag == 1;
    }
    public void getPersonNameByState(String State) {
        List<ContactOfPerson> list  = contactList.stream().filter(contactName ->contactName.getState().equals(State)).collect(Collectors.toList());
        for(ContactOfPerson contact: list){
            System.out.println("First Name: "+contact.getFirstName());
            System.out.println("Last Name: "+contact.getLastName());
        }

    }
    public void getPersonNameByCity(String cityName) {
        List<ContactOfPerson> list  = contactList.stream().filter(contactName ->contactName.getCity().equals(cityName)).collect(Collectors.toList());
        for(ContactOfPerson contact: list){
            System.out.println("First Name: "+contact.getFirstName());
            System.out.println("Last Name: "+contact.getLastName());
        }
    }
    //file IO operation
    public static void writeData(AddressBookMain addressBookMain) {
        StringBuffer personBuffer = new StringBuffer();
        contactList.forEach(person -> {
            String personDataString = person.toString().concat("\n");
            personBuffer.append(personDataString);
        });
        try {
            Files.write(Paths.get("Data.txt"), personBuffer.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readData(AddressBookMain addressBookMain) {
        try {
            Files.lines(new File("Data.txt").toPath()).map(String::trim).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
