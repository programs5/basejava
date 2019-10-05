package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    // сохранить новое резюме в хранилище
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Resume storage out of bounds");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume already present uuid=" + resume.getUuid());
        } else {
            storage[size] = resume;
            size++;
        }
    }

    // удалить резюме из хранилища по заданному uuid
    public void delete(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            System.out.println("Resume is not found uuid=" + uuid);
        } else {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    // вернуть индекс резюме по заданному uuid
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
