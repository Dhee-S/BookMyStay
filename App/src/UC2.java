abstract class Room {

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

    abstract void display();
}

/* Single Room */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0, 5);
    }

    void display() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
    }
}

/* Double Room */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0, 3);
    }

    void display() {
        System.out.println("Double Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
    }
}

/* Suite Room */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0, 2);
    }

    void display() {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
    }
}

public class UC2 {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization");

        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.display();
        doub.display();
        suite.display();
    }
}