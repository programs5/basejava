package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

public class MainTestArrayStorage {
    //private static final Storage STORAGE = new ArrayStorage();
    private static final Storage STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");

        System.out.println("Size: " + STORAGE.size());

        STORAGE.save(r4);
        STORAGE.save(r3);
        STORAGE.save(r2);
        STORAGE.save(r1);

        printAll();
        System.out.println("Size: " + STORAGE.size());

        System.out.println("Get r1: " + STORAGE.get(r1.getUuid()));
        System.out.println("Get r2: " + STORAGE.get(r2.getUuid()));
        System.out.println("Get r3: " + STORAGE.get(r3.getUuid()));
        System.out.println("Get r4: " + STORAGE.get(r4.getUuid()));
        System.out.println("Get dummy: " + STORAGE.get("dummy"));

        Resume rU = new Resume();
        rU.setUuid("uuid2");
        STORAGE.update(rU);
        printAll();

        STORAGE.delete(r2.getUuid());
        printAll();
        System.out.println("Size: " + STORAGE.size());

        STORAGE.delete("dummy");

        STORAGE.clear();
        printAll();
        System.out.println("Size: " + STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
