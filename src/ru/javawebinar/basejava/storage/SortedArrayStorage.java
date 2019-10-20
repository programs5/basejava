package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveSpecial(Resume resume, int idx) {
        idx = Math.abs(idx);
        System.arraycopy(storage, idx - 1, storage, idx, size - idx + 1);
        storage[idx - 1] = resume;
    }

    @Override
    protected void deleteSpecial(String uuid, int idx) {
        System.arraycopy(storage, idx + 1, storage, idx, size - idx - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}