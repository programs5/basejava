package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

// TODO реализовать проверку на переполнение overflow тоже !!!

public abstract class AbstractArrayStorageTest {

    private Storage storage = createStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    abstract Storage createStorage();



    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}