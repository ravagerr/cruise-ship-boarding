package classSolution;

public class Cabin {
    public Passenger passenger;
    private int occupants;

    public Cabin (Passenger passenger, int occupants) {
        this.passenger = passenger;
        this.occupants = occupants;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public int getOccupants() {
        return occupants;
    }
    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }
}
