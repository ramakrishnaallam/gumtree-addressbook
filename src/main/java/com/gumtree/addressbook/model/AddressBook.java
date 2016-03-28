package com.gumtree.addressbook.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressBook {
    List<Person> contacts;

    public AddressBook(List<Person> contacts) {
        if (contacts == null) {
            this.contacts = new ArrayList<>();
        } else {
            this.contacts = contacts;
        }
    }

    public List<Person> getContacts() {
        return contacts;
    }

    /**
     * gets the number of persons with the given Gender in the AddressBook.
     *
     * @param gender the gender.
     * @return number of people with the given gender.
     */
    public long getGenderCount(Gender gender) {
        return contacts.stream().filter(p -> gender.equals(p.getGender())).count();
    }

    // If multiple people are the oldest it would return the first one in the list.

    /**
     * finds the oldest person in the address book.
     *
     * @return returns the Optional containing the oldest Person.
     */
    public Optional<Person> findOldestPerson() {
        return contacts.stream().max(Person::compare);
    }

    /**
     * Gets the age difference between two given persons
     *
     * @param name1
     * @param name2
     * @return returns the Optional containing the age difference.
     */
    public Optional<Long> getAgeDifferenceInDays(String name1, String name2) {
        Optional<Person> person1 = findPerson(name1);
        Optional<Person> person2 = findPerson(name2);

        if (person1.isPresent() && person2.isPresent()) {
            LocalDate bDay1 = person1.get().getBirthDay();
            LocalDate bDay2 = person2.get().getBirthDay();
            long diff = ChronoUnit.DAYS.between(bDay1, bDay2);
            return Optional.of(diff);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Person> findPerson(String name) {
        return contacts.stream().filter(p -> name.equals(p.getName())).findFirst();
    }
}
