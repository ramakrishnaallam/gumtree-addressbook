package com.gumtree.addressbook.reader;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Implementation of the InputReader. This specific implementation reads from a given file
 * and prepares the AddressBook.
 */
public class FileDataReader implements InputReader {
    //default location of the Address Book file.
    private static String LOCATION = "/AddressBook";

    public FileDataReader(String location) {
        LOCATION = location;
    }

    @Override
    public AddressBook readData() {
        AddressBook addressBook;
        try (Stream<String> stream = Files.newBufferedReader((Paths.get(LOCATION))).lines()) {
            addressBook = new AddressBook(stream.map(Person::parsePerson).filter(o -> o.isPresent()).map(o -> o.get()).collect(
                    toList()));
        } catch (IOException e) {
            //Empty Address Book
            addressBook = new AddressBook(new ArrayList<Person>());
        }
        return addressBook;
    }
}
