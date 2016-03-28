package com.gumtree.addressbook.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class AddressBookTest {
    Person person;
    AddressBook addressBook;
    List<Person> contacts = new ArrayList<>();

    @Before
    public void setup() {
        contacts.add(Person.parsePerson("Bill McKnight, Male, 16/03/77").get());
        contacts.add(Person.parsePerson("Paul Robinson, Male, 15/01/85").get());
        contacts.add(Person.parsePerson("Gemma Lane, Female, 20/11/91").get());
        contacts.add(Person.parsePerson("Sarah Stonet, Female,20/09/80").get());
        contacts.add(Person.parsePerson("Wes Jackson, Male, 14/08/74").get());

        addressBook = new AddressBook(contacts);
    }

    @Test
    public void testGenderCount() {
        assertEquals(3, addressBook.getGenderCount(Gender.Male));
        assertEquals(2, addressBook.getGenderCount(Gender.Female));
    }

    @Test
    public void testGenderCountWhenNullGenderContactExistsInAddressBook() {
        Optional<Person> abc = (Person.parsePerson("Wes Jackson, null, 14/08/64"));
        if (abc.isPresent()) {
            contacts.add(abc.get());
        }

        assertEquals(3, addressBook.getGenderCount(Gender.Male));
        assertEquals(2, addressBook.getGenderCount(Gender.Female));
    }

    @Test
    public void testFindOldestPerson() {
        assertTrue(addressBook.findOldestPerson().isPresent());
        assertEquals("Wes Jackson", addressBook.findOldestPerson().get().getName());
    }

    @Test
    public void testWhenMultiplePersonsAreTheOldest() {
        contacts.add(Person.parsePerson("ABC, Female, 20/09/80").get());
        contacts.add(Person.parsePerson("XYZ, Male, 14/08/74").get());

        addressBook = new AddressBook(contacts);
        assertTrue(addressBook.findOldestPerson().isPresent());
        assertEquals("Wes Jackson", addressBook.findOldestPerson().get().getName());

    }

    @Test
    public void testOldestWithInvalidPerson() {
        Optional<Person> abc = (Person.parsePerson("ABC, null, 20/09/64"));
        Optional<Person> xyz = (Person.parsePerson("XYZ, 20/09/64"));
        if (abc.isPresent()) {
            contacts.add(abc.get());
        }
        if (xyz.isPresent()) {
            contacts.add(xyz.get());
        }
        addressBook = new AddressBook(contacts);
        assertEquals("Wes Jackson", addressBook.findOldestPerson().get().getName());
    }

    @Test
    public void testAgeDiff() {
        long diff1 = addressBook.getAgeDifferenceInDays("Bill McKnight", "Wes Jackson").get();
        long diff2 = addressBook.getAgeDifferenceInDays("Wes Jackson", "Bill McKnight").get();
        assertTrue(diff1 == -diff2);

        Optional<Person> abc = (Person.parsePerson("ABC, Male, 20/09/64"));
        Optional<Person> xyz = (Person.parsePerson("XYZ, Female, 20/09/64"));
        addressBook.getContacts().add(abc.get());
        addressBook.getContacts().add(xyz.get());
        long diff = addressBook.getAgeDifferenceInDays("ABC", "XYZ").get();
        assertTrue(diff == 0);
    }

    @Test
    public void testAgeDiffOfEqualBdays() {
        Optional<Person> abc = (Person.parsePerson("ABC, Male, 20/09/64"));
        Optional<Person> xyz = (Person.parsePerson("XYZ, Female, 20/09/64"));
        addressBook.getContacts().add(abc.get());
        addressBook.getContacts().add(xyz.get());
        long diff = addressBook.getAgeDifferenceInDays("ABC", "XYZ").get();
        assertTrue(diff == 0);
    }

    @Test
    public void testAgeDiffOfSamePerson() {
        Optional<Person> abc = (Person.parsePerson("ABC, Male, 20/09/64"));
        addressBook.getContacts().add(abc.get());
        long diff = addressBook.getAgeDifferenceInDays("ABC", "ABC").get();
        assertTrue(diff == 0);
    }

    @Test
    public void testAgeDiffIfOneisInvalidPerson() {
        Optional<Person> abc = (Person.parsePerson("ABC, Male, 20/09/64"));
        Optional<Person> xyz = (Person.parsePerson("XYZ, 20/09/64"));
        if (abc.isPresent()) {
            addressBook.getContacts().add(abc.get());
        }
        if (xyz.isPresent()) {
            addressBook.getContacts().add(xyz.get());
        }
        assertFalse(addressBook.getAgeDifferenceInDays("ABC", "XYZ").isPresent());
    }

    @Test
    public void testAgeDiffIfOnePersonNotPresent() {
        Optional<Person> abc = (Person.parsePerson("ABC, Male, 20/09/64"));
        if (abc.isPresent()) {
            addressBook.getContacts().add(abc.get());
        }
        assertFalse(addressBook.getAgeDifferenceInDays("ABC", "XYZ").isPresent());
    }

    @Test
    public void testAgeDiffIfBothPersonsNotPresent() {
        assertFalse(addressBook.getAgeDifferenceInDays("ABC", "XYZ").isPresent());
    }

    @Test
    public void testGenderCountWhenNoRecords(){
        AddressBook addressBook = new AddressBook(null);
        assertEquals(0, addressBook.getGenderCount(Gender.Female));
        assertEquals(0, addressBook.getGenderCount(Gender.Male));

        addressBook = new AddressBook(new ArrayList<Person>());
        assertEquals(0, addressBook.getGenderCount(Gender.Female));
        assertEquals(0, addressBook.getGenderCount(Gender.Male));
    }

    @Test
    public void testOldestPersonWhenNoRecords(){
        AddressBook addressBook = new AddressBook(null);
        assertFalse(addressBook.findOldestPerson().isPresent());

        addressBook = new AddressBook(new ArrayList<Person>());
        assertFalse(addressBook.findOldestPerson().isPresent());
    }

    @Test
    public void testAgeDiffWhenNoRecords(){
        AddressBook addressBook = new AddressBook(null);
        assertFalse(addressBook.getAgeDifferenceInDays("ABC","XYZ").isPresent());

        addressBook = new AddressBook(new ArrayList<Person>());
        assertFalse(addressBook.getAgeDifferenceInDays("ABC","XYZ").isPresent());
    }
}
