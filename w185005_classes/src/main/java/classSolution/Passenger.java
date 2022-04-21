package classSolution;

public class Passenger {

    private String firstName;
    private String lastName;
    private double expenses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public Passenger(String firstName, String lastName, double expenses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.expenses = expenses;
    }
}
