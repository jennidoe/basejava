import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume newResume) {
        if (newResume.getUuid() != null) {
            if (findResumeByUUID(newResume.getUuid()) == -1) {
                if (size < storage.length) {
                    storage[size] = newResume;
                    size++;
                } else System.out.println("Невозможно сохранить резюме, в storage нет места.");
            } else System.out.println("Невозможно сохранить резюме, такой uuid уже есть.");
        } else System.out.println("Невозможно сохранить резюме, uuid пустой.");
    }

    Resume get(String uuid) {
        if (uuid != null) {
            int index = findResumeByUUID(uuid);
            return index != -1 ? storage[index] : null;
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid != null) {
            int index = findResumeByUUID(uuid);
            if (index != -1) {
                size--;
                storage[index] = storage[size];
                storage[size] = null;
            } else System.out.println("Невозможно удалить элемент, элемент с таким uuid не найден.");
        } else System.out.println("Невозможно удалить элемент, uuid пустой.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findResumeByUUID(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
