import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume newResume) {
        if (newResume.getUuid() != null) {
            if (findResumeByUUID(newResume.getUuid()) == -1) {
                int index = findFirstNull();
                if (index != -1) {
                    storage[index] = newResume;
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
                storage[index] = null;
            } else System.out.println("Невозможно удалить элемент, элемент с таким uuid не найден.");
            sortStorage();
        } else System.out.println("Невозможно удалить элемент, uuid пустой.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = findFirstNull();
        return Arrays.copyOf(storage,
                index != -1 ? index : storage.length);
    }

    int size() {
        int index = findFirstNull();
        return index != 1 ? index : storage.length;
    }

    private int findResumeByUUID(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstNull() {
        sortStorage();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void sortStorage() {
        Arrays.sort(storage, (o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            if (o1 == null) return 1;
            if (o2 == null) return -1;
            return o1.getUuid().compareTo(o2.getUuid());
        });

    }
}
