import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* ---------- Inventory Model (Serializable) ---------- */

class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}


/* ---------- Persistence Service ---------- */

class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    // SAVE
    public static void save(RoomInventory inventory) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // LOAD
    public static RoomInventory load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (RoomInventory) ois.readObject();

        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new RoomInventory();
        }
    }
}


/* ---------- Application Entry ---------- */

public class UC12 {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        // LOAD existing state
        RoomInventory inventory = PersistenceService.load();

        // DISPLAY recovered or default state
        inventory.displayInventory();

        // SAVE state before shutdown
        PersistenceService.save(inventory);
    }
}