package com.gumtree.addressbook.factory;

import com.gumtree.addressbook.model.AddressBook;
import com.gumtree.addressbook.reader.InputReader;

/**
 * Factory class for the AddressBook.
 */
public class AddressBookFactory {

    private InputReader reader;

    public AddressBookFactory(InputReader reader) {
        this.reader = reader;
    }

    public AddressBook create() {
        return reader.readData();
    }
}
