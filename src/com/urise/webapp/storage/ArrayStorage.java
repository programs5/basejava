package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    // удалить все резюме в хранилище
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    // найти заданное резюме в хранилище
    public void update(Resume resume) {
        int idx = find(resume.uuid);
        if (idx == -1) {
            System.out.println("UpdateError: Is Not Resume Found");
        } else {
            storage[idx] = resume;
        }
    }

    // сохранить новое резюме в хранилище
    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Error: Array Index Out Of Bounds");
        } else if (find(resume.uuid) >= 0) {
            System.out.println("SaveError: Resume Already Present");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    // вернуть резюме из хранилища по заданному uuid
    public Resume get(String uuid) {
        int idx = find(uuid);
        if (idx == -1) {
            return null;
        } else {
            return storage[idx];
        }
    }

    // удалить резюме из хранилища по заданному uuid
    public void delete(String uuid) {
        int idx = find(uuid);
        if (idx == -1) {
            System.out.println("DeleteError: Is No Resume Found");
        } else {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    // вернуть все резюме из хранилища
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    // вернуть количество резюме в хранилище
    public int size() {
        return size;
    }

    // найти резюме в хранилище по заданному uuid
    private int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
