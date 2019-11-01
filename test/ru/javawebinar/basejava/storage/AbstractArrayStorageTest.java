package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_DUMMY = "uuid_dummy";
    private static final Resume RESUME_UUID_1 = new Resume(UUID_1);
    private static final Resume RESUME_UUID_2 = new Resume(UUID_2);
    private static final Resume RESUME_UUID_3 = new Resume(UUID_3);
    private static final Resume RESUME_DUMMY = new Resume(UUID_DUMMY);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID_1);
        storage.save(RESUME_UUID_2);
        storage.save(RESUME_UUID_3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume oldResume = storage.get(UUID_1);
        storage.update(new Resume(UUID_1));
        Assert.assertNotSame(oldResume, storage.get(UUID_1));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_DUMMY);
        Assert.assertEquals(4, storage.size());
        Assert.assertSame(RESUME_DUMMY, storage.get(UUID_DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_UUID_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        for (int i = storage.size() + 1; i <= storage.STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("uuid" + storage.STORAGE_LIMIT + 1));
        Assert.fail("StorageException not thrown");
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_DUMMY);
    }

    @Test
    public void getAll() throws Exception {
        for (Resume resume : storage.getAll()) {
            Assert.assertSame(resume, storage.get(resume.getUuid()));
        }
        Assert.assertEquals(3, storage.getAll().length);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_UUID_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_DUMMY);
    }
}