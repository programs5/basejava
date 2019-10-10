package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveSpecial(Resume resume) {
        int idx = Math.abs(getIndex(resume.getUuid()));
        if (size > 0) {
            System.arraycopy(storage, idx - 1, storage, idx, size - idx + 1);
        }
        storage[idx - 1] = resume;
    }

    @Override
    protected void deleteSpecial(String uuid) {
        int idx = getIndex(uuid);
        if (idx < size - 1) {
            System.arraycopy(storage, idx + 1, storage, idx, size - idx - 1);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}