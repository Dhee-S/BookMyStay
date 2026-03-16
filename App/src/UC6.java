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


/* ---------- Booking Queue ---------- */

class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/* ---------- Room Allocation ---------- */

class RoomAllocationService {

    private Map<String, Integer> roomCounter = new HashMap<>();

    public void allocateRoom(Reservation r) {

        String type = r.getRoomType();

        int count = roomCounter.getOrDefault(type, 0) + 1;

        roomCounter.put(type, count);

        String roomId = type + "-" + count;

        System.out.println("Booking confirmed for Guest: "
                + r.getGuestName()
                + ", Room ID: "
                + roomId);
    }
}


/* ---------- Application ---------- */

public class UC6 {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocator = new RoomAllocationService();

        // Booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // FIFO processing
        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();

            allocator.allocateRoom(r);
        }
    }
}