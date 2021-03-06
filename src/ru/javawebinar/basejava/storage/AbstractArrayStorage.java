package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    protected static final int STORAGE_LIMIT = 10_000;

    // вернуть количество резюме в хранилище
    public int size() {
        return size;
    }

    // удалить все резюме в хранилище
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    // обновить заданное резюме в хранилище
    public void update(Resume resume) {
        int idx = getIndex(resume.getUuid());
        if (idx < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage[idx] = resume;
    }

    // сохранить новое резюме в хранилище
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        int idx = getIndex(resume.getUuid());
        if (idx >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveSpecial(resume, idx);
        size++;
    }

    // удалить резюме из хранилища по заданному uuid
    public void delete(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteSpecial(uuid, idx);
        storage[size - 1] = null;
        size--;
    }

    // вернуть резюме из хранилища по заданному uuid
    public Resume get(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[idx];
    }

    // вернуть все резюме из хранилища
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void saveSpecial(Resume resume, int idx);

    protected abstract void deleteSpecial(String uuid, int idx);

    // вернуть индекс резюме по заданному uuid
    protected abstract int getIndex(String uuid);
}