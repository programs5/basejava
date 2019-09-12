import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    // полностью очищаем хранилище
    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    // добавляем новый элемент в хранилище
    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ArrayIndexOutOfBounds");
            return;
        }
        storage[size] = r;
        size++;
    }

    // возвращаем элемент по заданному uuid
    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    // удаляем элемент с заданным uuid из очереди
    void delete(String uuid) {
        boolean isDeleted = false;
        int s = size;
        for (int i = 0; i < s; i++) {
            if (isDeleted) {
                storage[i - 1] = storage[i];
                storage[i] = null;
            } else if (storage[i].uuid == uuid) {
                storage[i] = null;
                isDeleted = true;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    // возвращем количество элементов хранилища
    int size() {
        return size;
    }
}
