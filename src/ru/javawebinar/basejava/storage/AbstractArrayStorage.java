package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

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
            System.out.println("Resume is not found uuid=" + resume.getUuid());
        } else {
            storage[idx] = resume;
        }
    }

    // сохранить новое резюме в хранилище
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume storage out of bounds");
            return;
        }
        int idx = getIndex(resume.getUuid());
        if (idx >= 0) {
            System.out.println("Resume already present uuid=" + resume.getUuid());
        } else {
            saveSpecial(resume, idx);
            size++;
        }
    }

    // удалить резюме из хранилища по заданному uuid
    public void delete(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            System.out.println("Resume is not found uuid=" + uuid);
        } else {
            deleteSpecial(uuid, idx);
            storage[size - 1] = null;
            size--;
        }
    }

    // вернуть резюме из хранилища по заданному uuid
    public Resume get(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            System.out.println("Resume is not found uuid=" + uuid);
            return null;
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