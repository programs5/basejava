/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    // CLEAR
    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    // FIND
    int find(String uid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid == uid) {
                return i;
            }
        }
        return -1;
    }

    // UPDATE
    void update(Resume r) {
        int idx = find(r.uuid);
        if(idx >= 0)
            storage[idx] = r;
        else
            System.out.println("UpdateError: Is No Resume Found");
    }

    // SAVE
    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Error: Array Index Out Of Bounds");
            return;
        }
        int idx = find(r.uuid);
        if(idx >= 0) {
            System.out.println("SaveError: Resume Already Present");
            return;
        }
        storage[size] = r;
        size++;
        /*
        // TODO check if resume not present (out error message)
        if (size == storage.length) {
            System.out.println("Error: Array Index Out Of Bounds");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i] == r) {
                System.out.println("SaveError: Resume Already Present");
                return;
            }
        }
        storage[size] = r;
        size++;
        */
    }

    // GET
    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    // DELETE
    void delete(String uuid) {
        int idx = find(uuid);
        if(idx >= 0) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("DeleteError: Is No Resume Found");
        /*
        // TODO check if resume present (out error message)
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("DeleteError: Is No Resume Found");
        */
    }

    // GET ALL
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    // SIZE
    int size() {
        return size;
    }
}
