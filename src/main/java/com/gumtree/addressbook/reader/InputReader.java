package com.gumtree.addressbook.reader;

import com.gumtree.addressbook.model.AddressBook;

/**
 * This interface defines the API for reading the data needed for the AddressBook
 * The sub classes should implement the readData method
 */
public interface InputReader {
    /**
     * Reads the data from the source.
     *
     * @return the AddressBook created from the source.
     */
    AddressBook readData();
}
