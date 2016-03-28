package com.gumtree.addressbook.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PersonTest {
    @Test
    public void testOlderPerson() {
        Person p1 = Person.parsePerson("Bill McKnight, Male, 16/03/77").get();
        Person p2 = Person.parsePerson("Paul Robinson, Male, 15/01/85").get();
        Person p3 = Person.parsePerson("ABC, Male, 16/03/77").get();

        assertTrue(Person.compare(p1, p2) > 0);
        assertTrue(Person.compare(p2, p1) < 0);
        assertTrue(Person.compare(p1, p3) == 0);
    }
}
