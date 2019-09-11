import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    // полностью очищаем хранилище
    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;
            else {
                storage[i] = null;
                size--;
            }
        }
    }

    // добавляем новый элемент в хранилище
    void save(Resume r) {
        // если хранилище заполнено, гененрируем ошибку
        if (size == 10000)
            throw new ArrayIndexOutOfBoundsException();
        // добавляем элемент
        storage[size] = r;
        size++;
    }

    // возвращаем элемент по заданному uuid
    Resume get(String uuid) {
        int idx = -1;
        for (int i = 0; i < storage.length; i++) {
            // если перебрали все элементы и не нашли нужного, генерируем ошибку
            if (storage[i] == null) {
                throw new NoSuchElementException(uuid);
            } else if (storage[i].uuid == uuid) {
                idx = i;
                break;
            }
        }
        return storage[idx];
    }

    // удаляем элемент с заданным uuid из очереди
    void delete(String uuid) {
        boolean isDeleted = false;
        for (int i = 0; i < storage.length; i++) {
            // если перебрали все элементы и не нашли нужного, генерируем ошибку
            if (!isDeleted && storage[i] == null) {
                throw new NoSuchElementException(uuid);
            }
            // если удалили элемент и больше нет элементов, завершаем
            if (isDeleted && storage[i] == null) {
                break;
            }
            // если удалили и есть еще элементы, смещаем их на место удаленного, закрываем все дырки
            if (isDeleted && storage[i] != null) {
                storage[i - 1] = storage[i];
                storage[i] = null;
            }
            // ищем элемент и если совпадает удаляем его
            if (!isDeleted && storage[i] != null) {
                if (storage[i].uuid == uuid) {
                    storage[i] = null;
                    isDeleted = true;
                    size--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                resumes[i] = new Resume();
                resumes[i].uuid = storage[i].uuid;
            }
        }
        return resumes;
    }

    // возвращем количество элементов хранилища
    int size() {
        return size;
    }
}
