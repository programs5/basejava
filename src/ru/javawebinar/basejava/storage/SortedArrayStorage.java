package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Resume storage out of bounds");
            return;
        }
        int idx = getIndex(resume.getUuid());
        if (idx >= 0) {
            System.out.println("Resume already present uuid=" + resume.getUuid());
            return;
        }
        idx = Math.abs(idx);
        if (size > 0) {
            System.arraycopy(storage, idx - 1, storage, idx, size - idx + 1);
        }
        storage[idx - 1] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int idx = getIndex(uuid);
        if (idx < 0) {
            System.out.println("Resume is not found uuid=" + uuid);
            return;
        } else if (idx < size - 1) {
            System.arraycopy(storage, idx + 1, storage, idx, size - idx - 1);
        }
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
