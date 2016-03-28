package com.gumtree.addressbook.reader;

import com.gumtree.addressbook.model.AddressBook;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FileDataReaderTest {
    @Test
    public void testProperData() {
        FileDataReader reader = new FileDataReader("src/test/resources/AddressBook");
        AddressBook addressBook = reader.readData();
        assertEquals(5, addressBook.getContacts().size());
    }

    @Test
    public void testInvalidData() {
        FileDataReader reader = new FileDataReader("src/test/resources/AddressBook_InvalidFormat");
        AddressBook addressBook = reader.readData();
        assertEquals(4, addressBook.getContacts().size());
    }

    @Test
    public void testZeroRecordsData() {
        FileDataReader reader = new FileDataReader("src/test/resources/AddressBook_NoRecords");
        AddressBook addressBook = reader.readData();
        assertEquals(0, addressBook.getContacts().size());
    }

    @Test
    public void testNoFile() {
        FileDataReader reader = new FileDataReader("src/test/resources/AddressBook_NotExisting");
        AddressBook addressBook = reader.readData();
        assertEquals(0, addressBook.getContacts().size());
    }
}
