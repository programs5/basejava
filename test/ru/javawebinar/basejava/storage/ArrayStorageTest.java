package ru.javawebinar.basejava.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    @Override
    protected Storage createStorage() {
        return new ArrayStorage();
    }

}