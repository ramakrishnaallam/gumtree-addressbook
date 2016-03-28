package com.gumtree.addressbook.factory;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.model.Person;
import com.gumtree.addressbook.reader.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.*;

public class AddressBookFactoryTest {

    AddressBookFactory factory;
    InputReader mockReader;

    @Before
    public void setUp(){
        mockReader = createNiceMock(InputReader.class);
        Person p1 = Person.parsePerson("A, Male, 16/03/77").get();
        Person p2 = Person.parsePerson("A, Female, 16/03/74").get();
        Optional<Person> p3 = Person.parsePerson("A, dummy, 16/03/74");

        List<Person> contacts = new ArrayList<>();
        contacts.add(p1);
        contacts.add(p2);
        if(p3.isPresent()){
            contacts.add(p3.get());
        }

        AddressBook book = new AddressBook(contacts);
        expect(mockReader.readData()).andReturn(book);
        replay(mockReader);
        factory = new AddressBookFactory(mockReader);
    }
    @Test
    public void testCreate(){
        AddressBook book = factory.create();
        assertTrue(book.getContacts().size()  == 2);
    }
}
