import java.util.LinkedList;
import java.util.Queue;

/* ---------- Reservation Class ---------- */

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


/* ---------- Booking Request Queue ---------- */

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    // Process requests (FIFO)
    public void processRequests() {

        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType()
            );
        }
    }
}


/* ---------- Main Application ---------- */

public class UC5 {

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Guest booking requests
        requestQueue.addRequest(new Reservation("Abhi", "Single"));
        requestQueue.addRequest(new Reservation("Subha", "Double"));
        requestQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process queue
        requestQueue.processRequests();
    }
}