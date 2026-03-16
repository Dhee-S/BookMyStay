import java.util.HashMap;
import java.util.Map;

/* Room Class */
class Room {

    int beds;
    int size;
    double price;
    int available;

    public Room(int beds, int size, double price, int available) {
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.available = available;
    }
    public void display(String roomType) {
        System.out.println(roomType + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + available);
    }
}


/* Inventory Management */
class RoomInventory {

    private HashMap<String, Room> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", new Room(1, 250, 1500.0, 5));
        inventory.put("Double Room", new Room(2, 400, 2500.0, 3));
        inventory.put("Suite Room", new Room(3, 750, 5000.0, 2));
    }

    public void displayInventory() {

        System.out.println("Hotel Room Inventory Status");

        for (Map.Entry<String, Room> entry : inventory.entrySet()) {

            entry.getValue().display(entry.getKey());
        }
    }
}


/* Main Application */
public class UC3 {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

    }
}