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
    private static final int COUNT_RESUME = 3;
    private static final int STORAGE_LIMIT = 10_000;
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
        Assert.assertEquals(COUNT_RESUME, storage.size());
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
        Assert.assertEquals(COUNT_RESUME, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void save() throws Exception {
        try {
            storage.save(RESUME_DUMMY);
            Assert.assertEquals(COUNT_RESUME + 1, storage.size());
        } catch (StorageException e) {
            storage.delete(UUID_1);
            storage.save(RESUME_DUMMY);
            Assert.assertEquals(COUNT_RESUME, storage.size());
        }
        Assert.assertSame(RESUME_DUMMY, storage.get(UUID_DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        try {
            storage.save(RESUME_UUID_3);
        } catch (StorageException e) {
            storage.delete(UUID_1);
            storage.save(RESUME_UUID_3);
        }
    }

    @Test
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int n = 1; n <= STORAGE_LIMIT + 1; n++) {
                storage.save(new Resume("uuid_" + n));
            }
            Assert.fail("StorageException not thrown");
        } catch (StorageException e) {
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Unknown Exception thrown");
        }
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(COUNT_RESUME - 1, storage.size());
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
        Assert.assertEquals(COUNT_RESUME, storage.getAll().length);
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