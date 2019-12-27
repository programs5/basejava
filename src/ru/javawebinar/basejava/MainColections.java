package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.util.ArrayList;
import java.util.Collection;

public class MainColections {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_UUID_1 = new Resume(UUID_1);
    private static final Resume RESUME_UUID_2 = new Resume(UUID_2);
    private static final Resume RESUME_UUID_3 = new Resume(UUID_3);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_UUID_1);
        collection.add(RESUME_UUID_2);
        collection.add(RESUME_UUID_3);
        for(Resume r : collection){
            System.out.println(r);
        }


    }
}
