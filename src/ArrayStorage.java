import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    // FIND
    private int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return i;
        }
        return -1;
    }

    // CLEAR
    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    // UPDATE
    void update(Resume r) {
        int idx = find(r.uuid);
        if (idx == -1)
            System.out.println("UpdateError: Is Not Resume Found");
        else
            storage[idx] = r;
    }

    // SAVE
    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Error: Array Index Out Of Bounds");
        } else if (find(r.uuid) >= 0) {
            System.out.println("SaveError: Resume Already Present");
        } else {
            storage[size] = r;
            size++;
        }
    }

    // GET
    Resume get(String uuid) {
        int idx = find(uuid);
        if (idx == -1)
            return null;
        else
            return storage[idx];
    }

    // DELETE
    void delete(String uuid) {
        int idx = find(uuid);
        if (idx == -1)
            System.out.println("DeleteError: Is No Resume Found");
        else {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    // GET ALL
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    // SIZE
    int size() {
        return size;
    }
}
