import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    Resume[] storage = new Resume[10000];

    void clear() {

    }

    // save Resume
    void save(Resume r) {
        boolean isFull = true;

        for(int i= 0; i < storage.length; i++){
            if(storage[i] == null) {
                storage[i] = r;
                isFull = false;
                break;
            }
        }
        if(isFull) throw new ArrayIndexOutOfBoundsException();
    }

    // get Resume
    Resume get(String uuid) {

        int idx = -1;

        for(int i= 0; i < storage.length; i++){

            if(storage[i] == null) throw new NoSuchElementException(uuid);

            if(storage[i].uuid == uuid) {
                idx= i;
                break;
            }
        }
        return storage[idx];
    }

    void delete(String uuid) {

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] resumes;
        int count = size();
        resumes = new Resume[count];

        if(count > 0){
            for(int i= 0; i < count; i++) {
                resumes[i] = new Resume();
                resumes[i].uuid = storage[i].uuid;
            }
        }
        return resumes;
    }

    // size
    int size() {
        int size = 0;

        for(int i= 0; i < storage.length; i++){

            if(storage[i] == null) {
                size = i;
                break;
            }
        }
        return size;
    }
}
