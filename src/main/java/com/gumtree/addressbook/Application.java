package com.gumtree.addressbook;

import com.gumtree.addressbook.factory.AddressBookFactory;
import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Gender;
import com.gumtree.addressbook.model.Person;
import com.gumtree.addressbook.reader.FileDataReader;

import java.util.Optional;

public class Application {
    private static final String LOCATION = "src/main/resources/AddressBook";

    public static void main(String[] args) {
        AddressBookFactory factory = new AddressBookFactory(new FileDataReader(LOCATION));
        AddressBook addressBook = factory.create();
        //How many males are in the address book?
        System.out.println("############################ OUTPUT STARTS ##########################");
        System.out.printf("1. Number Of Males: [%s]. %n", addressBook.getGenderCount(Gender.Male));
        //Who is the oldest person in the address book?
        System.out.printf("2. Oldest Person's name: [%s]. %n", addressBook.findOldestPerson().map(Person::getName).orElse("Not Found"));
        //How many days older is Bill than Paul?
        Optional<Long> old = addressBook.getAgeDifferenceInDays("Bill McKnight", "Paul Robinson");
        long numberOfDays = 0;
        if (old.isPresent()) {
            numberOfDays = old.get();
        }
        System.out.printf("3. Bill is Older than Paul by [%s] days.%n", numberOfDays);
        System.out.println("############################ OUTPUT ENDS ############################");
    }
}
