package com.gumtree.addressbook.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Denotes the Person in the AddressBook.
 */
public class Person {
    private final String name;
    private final Gender gender;
    private final LocalDate birthDay;
    private static final String DELIMITER = ",";

    private Person(String name, Gender gender, LocalDate birthDay) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public static int compare(Person p1, Person p2) {
        return p2.getBirthDay().compareTo(p1.getBirthDay());
    }

    /**
     * Parses the String to the Person Object and returns the Optional containing the Person.
     * If the input String does not follow the format, it would return Optional of empty.
     *
     * @param personStr Should follow the format: "name, Gender, dd/MM/yy"
     * @return the Optional containing the Person.
     */
    public static Optional<Person> parsePerson(String personStr) {
        try {
            //Format: Bill McKnight, Male, 16/03/77
            String[] splitArray = personStr.split(DELIMITER);
            String name = splitArray[0].trim();
            Gender gender = Gender.valueOf(splitArray[1].trim());
            // deducting 100 yrs as the Date will be taken as 20th century
            LocalDate bDay = LocalDate.parse(splitArray[2].trim(), DateTimeFormatter.ofPattern("dd/MM/yy")).minusYears(100);
            return Optional.of(new Person(name, gender, bDay));
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    @Override
    public String toString() {
        return String.format("Person{name=%s, gender=%s, birthDay=%s}", name, gender, birthDay);
    }
}
