/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    // FIND
    int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    // CLEAR
    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    // UPDATE
    void update(Resume r) {
        int idx = find(r.uuid);
        if (idx >= 0)
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
        if (find(r.uuid) >= 0) {
            System.out.println("SaveError: Resume Already Present");
            return;
        }
        storage[size] = r;
        size++;
    }

    // GET
    Resume get(String uuid) {
        int idx = find(uuid);
        if (idx >= 0) {
            return storage[idx];
        }
        return null;
    }

    // DELETE
    void delete(String uuid) {
        int idx = find(uuid);
        if (idx >= 0) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("DeleteError: Is No Resume Found");
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
