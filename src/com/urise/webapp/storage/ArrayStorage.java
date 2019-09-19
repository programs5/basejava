package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    // удалить все резюме в хранилище
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    // найти заданное резюме в хранилище
    public void update(Resume resume) {
        int idx = find(resume.getUuid());
        if (idx >= 0) {
            storage[idx] = resume;
        }
    }

    // сохранить новое резюме в хранилище
    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Resume storage out of bounds");
        } else if (find(resume.getUuid()) >= 0) {
            System.out.println("Resume already present uuid=" + resume.getUuid());
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
        if (idx >= 0) {
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
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("Resume is not found uuid=" + uuid);
        return -1;
    }
}
