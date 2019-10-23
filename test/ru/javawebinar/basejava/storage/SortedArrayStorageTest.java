package ru.javawebinar.basejava.storage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    @Override
    protected Storage createStorage() {
        return new SortedArrayStorage();
    }
}