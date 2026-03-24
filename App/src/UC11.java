import java.util.*;

/* ---------- Reservation ---------- */

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/* ---------- Shared Queue ---------- */

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}


/* ---------- Inventory (Thread-Safe) ---------- */

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public synchronized boolean allocate(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    public void displayInventory() {
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}


/* ---------- Allocation Service ---------- */

class BookingProcessor implements Runnable {

    private BookingQueue queue;
    private RoomInventory inventory;
    private Map<String, Integer> counter;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory, Map<String, Integer> counter) {
        this.queue = queue;
        this.inventory = inventory;
        this.counter = counter;
    }

    public void run() {

        Reservation r;

        while ((r = queue.getRequest()) != null) {

            synchronized (inventory) {

                if (inventory.allocate(r.getRoomType())) {

                    int count = counter.getOrDefault(r.getRoomType(), 0) + 1;
                    counter.put(r.getRoomType(), count);

                    String roomId = r.getRoomType() + "-" + count;

                    System.out.println("Booking confirmed for Guest: "
                            + r.getGuestName()
                            + ", Room ID: "
                            + roomId);
                }
            }
        }
    }
}


/* ---------- Main ---------- */

public class UC11 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Concurrent Booking Simulation");

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> counter = new HashMap<>();

        // Simulated concurrent requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Double"));
        queue.addRequest(new Reservation("Kural", "Suite"));
        queue.addRequest(new Reservation("Subha", "Single"));

        // Create threads
        Thread t1 = new Thread(new BookingProcessor(queue, inventory, counter));
        Thread t2 = new Thread(new BookingProcessor(queue, inventory, counter));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        inventory.displayInventory();
    }
}