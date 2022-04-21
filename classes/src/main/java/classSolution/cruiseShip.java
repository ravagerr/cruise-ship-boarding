package classSolution;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class cruiseShip {

    public static void main(String[] args) {
        String menu;
        Cabin[] cruiseShipz = new Cabin[13];
        Scanner scanner = new Scanner(System.in);

        initialiser(cruiseShipz);

        while (true) { //While loop ensures program always comes back to the menu unless 'x' is used to quit.
            System.out.println("Menu");
            System.out.println("Enter 'V' to View All Cabins: ");
            System.out.println("Enter 'E' to View only Empty Cabins: ");
            System.out.println("Enter 'A' to Add a Passenger to a Cabin: ");
            System.out.println("Enter 'D' to Delete a Customer from a Cabin: ");
            System.out.println("Enter 'F' to Find a Passenger: ");
            System.out.println("Enter 'S' to Save Program data: ");
            System.out.println("Enter 'L' to Load Program Data: ");
            System.out.println("Enter 'O' to view Passengers sorted Alphabetically: ");
            System.out.println("Enter 'X' to Exit:");
            System.out.println("Enter 'T' to view Expenses:");
            System.out.println("Enter Option: ");
            menu = scanner.nextLine().toUpperCase();

            switch (menu) { //Switch is used for the main navigation
                case ("A"):
                    addPassengers(cruiseShipz);
                    break;

                case ("E"):
                    emptyCabins(cruiseShipz);
                    break;

                case ("V"):
                    viewCabins(cruiseShipz);
                    break;

                case ("O"):
                    sortPassengers(cruiseShipz);
                    break;

                case ("D"):
                    deletePassengers(cruiseShipz);
                    break;

                case ("F"):
                    findPassengers(cruiseShipz);
                    break;

                case ("S"):
                    saveFile(cruiseShipz);
                    break;

                case ("L"):
                    loadFile(cruiseShipz);
                    break;

                case ("T"):
                    showExpenses(cruiseShipz);
                    break;


                case ("X"):
                    System.out.println("Program Quit.");
                    System.exit(1);

                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }

    public static void initialiser(Cabin[] cabinsReference) {
        Passenger passenger = new Passenger("", "", 0);
        for (int i = 1; i < 13; i++) {
            cabinsReference[i] = new Cabin(passenger, 0); //Assigns an array of cabin objects

        }
    }

    public static void addPassengers(Cabin[] cabinsReference) {
        while (true) {
            boolean empty = false;
            for (int i = 1; i < cabinsReference.length; i++)
                if (cabinsReference[i].getOccupants() == 0) { //Checks if there are available cabins. If all are full, will display message
                    empty = true;
                    break;
                }

            Scanner scanner = new Scanner(System.in);
            int cabinID;
            try {
                System.out.println("Choose cabin number 1-12 or 13 to Stop:");
                cabinID = scanner.nextInt();

                if (empty) {
                    if (cabinID < 13 && cabinID > 0) {
                        cabinsReference[cabinID] = cabinAdder(cabinID); //Refers to cabinAdder function and executes it
                    } else if (cabinID > 13 || cabinID <= 0) {
                        System.out.println("Out of range input. Please choose from 1-12");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("All cabins are full. Try deleting some passengers.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }
    }

    private static Cabin cabinAdder(int cabinID) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Passengers' First Name for Cabin " + cabinID + " :");
        String cabinFirstName = scanner.next();

        while (true) { //These ensure proper input. while (true) can be replaced with the statement inside the if to simplify code. I am running out of time, so this works.
            if (!HasNoNumbers(cabinFirstName)) {
                System.out.println("Invalid First Name. Try again.");
                System.out.println("Passengers' First Name for Cabin " + cabinID + " :");
                cabinFirstName = scanner.next();
            } else {
                break;
            }
        }

        System.out.println("Passengers' Last Name for Cabin " + cabinID + " :");
        String cabinLastName = scanner.next();

        while (true) {
            if (!HasNoNumbers(cabinLastName)) {
                System.out.println("Invalid Last Name. Try again");
                System.out.println("Passengers' Last Name for Cabin " + cabinID + " :");
                cabinLastName = scanner.next();
            } else {
                break;
            }
        }

        System.out.println("How many guests will there be in Cabin " + cabinID + " (Max 2): ");
        int numOccupants = scanner.nextInt();

        while (true) {
            if (numOccupants > 2 || (numOccupants < 0)) {
                System.out.println("Maximum guests - 2. Try again.");
                System.out.println("How many occupants will there be in Cabin " + cabinID + " :"); //Prevents adding more than 2 occupants
                numOccupants = scanner.nextInt();
            } else {
                break;
            }
        }

        System.out.println("Expenses for customer in Cabin " + cabinID + " :");
        double expenses = scanner.nextDouble();

        while(true) {
            if(expenses <0 ) {
                System.out.println("Negative expenses not allowed. Try again with a positive number.");
                System.out.println("Expenses for customer in Cabin " + cabinID + " :");
                expenses = scanner.nextDouble();
            } else {
                break;
            }
        }
        System.out.println("Adding Customer to Cabin Number " + cabinID + " Successful");
        Passenger passenger = new Passenger(cabinFirstName, cabinLastName, expenses);
        return new Cabin(passenger, numOccupants);
    }


    private static boolean HasNoNumbers(String value) {
        String[] Numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "%", "!", "@", "£", "$", "^", "&", "*", "(", ")", "{", "}", ",", "|", ">", "<", ";", ":", "-", "+", "~", "±", "§", "_", "="};
        for (String i : Numbers)
            if (value.contains(i))
                return false;
        return true; //a non-regex solution to check for illegal characters in first and last name
    }

    public static void viewCabins(Cabin[] cabinsReference) {
        System.out.println("\n");
        System.out.println("All Cabins");


        for (int i = 1; i < 13; i++) { //Loops around all cabins and prints either as empty or with first name, last name and number of guests
            if (cabinsReference[i].passenger.getFirstName().equals("")) {
                System.out.println("Cabin " + i + " is Empty");
            } else
                System.out.println("Cabin " + i + " taken by " + cabinsReference[i].getPassenger().getFirstName() + " " + cabinsReference[i].getPassenger().getLastName() + " Guests: " + cabinsReference[i].getOccupants());
        }
    }

    public static void emptyCabins(Cabin[] cabinsReference) {
        System.out.println("\n");
        System.out.println("Empty Cabins: ");

        for (int i = 1; i < 13; i++) {
            if (cabinsReference[i].passenger.getFirstName().equals("")) { //Prints only the empty cabins, if some are not empty, they will not be displayed
                System.out.println("Cabin " + i + " is Empty");
            }
        }
    }

    public static void sortPassengers(Cabin[] cabinsReference) {
        String[] occupants = new String[cabinsReference.length];

        System.out.println("\n");
        System.out.println("Passengers alphabetically sorted: ");

        for (int i = 1; i < cabinsReference.length; i++) {
            occupants[i] = cabinsReference[i].getPassenger().getFirstName();
        }

        for (int i = 1; i < occupants.length - 1; i++) { //Nested for loop found in references
            for (int j = i + 1; j < occupants.length; j++) {
                if (occupants[i].compareTo(occupants[j]) > 0) {
                    String temp = occupants[i];
                    occupants[i] = occupants[j];
                    occupants[j] = temp;
                }
            }
        }

        for (String guest : occupants) {
            System.out.println(guest);
        }
    }

    public static void deletePassengers(Cabin[] cabinsReference) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int cabinID;
            try {
                System.out.println("Choose cabin number 1-12 to delete or 13 to Stop:");
                cabinID = scanner.nextInt();

                if (cabinID < 13 && cabinID > 0) {
                    System.out.println("Enter First Name at " + cabinID + " to confirm the deletion:");
                    String cabinPassengerName = scanner.next();

                    if (cabinPassengerName.equalsIgnoreCase(cabinsReference[cabinID].getPassenger().getFirstName())) {
                        cabinsReference[cabinID].getPassenger().setFirstName("");
                        cabinsReference[cabinID].getPassenger().setLastName("");
                        cabinsReference[cabinID].getPassenger().setExpenses(0);
                        cabinsReference[cabinID].setOccupants(0); //Sets all fields in classes to empty
                        System.out.println("\n");
                        System.out.println("Passenger " + cabinPassengerName + " at " + cabinID + " deleted successfully.");
                    } else {
                        System.out.println("Customer Name Invalid");
                    }
                } else if (cabinID > 13 || cabinID < 0) {
                    System.out.println("Out of range input. Try 1-12 ");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again with 1-12");
            }
        }
    }

    public static void findPassengers(Cabin[] cabinsReference) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int index;
            System.out.println("Enter First Name to find passenger or 'x' to stop: ");
            String cabinPassengerName = scanner.next();

            if (!(cabinPassengerName.equalsIgnoreCase("x"))) {
                for (index = 1; index < cabinsReference.length; index++) {
                    if (cabinsReference[index].getPassenger().getFirstName().equals(cabinPassengerName)) {
                        System.out.println(cabinPassengerName + " is located at cabin" + index);
                        break;
                    } else if (cabinsReference.length - 1 == index) {
                        System.out.println("Invalid passenger name. Try again.");
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void saveFile(Cabin[] cabinsReference) { //saving and loading references are available
        try {
            File myObject = new File("src/filename.txt");
            if (myObject.createNewFile()) {
                System.out.println("File created: " + myObject.getName());
            } else {
                System.out.println("File is already created");
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
        try {
            FileWriter myWriter = new FileWriter("src/filename.txt");
            for (int i = 1; i < 13; i++) {
                myWriter.write(i + "-" + cabinsReference[i].getPassenger().getFirstName() + "-" + cabinsReference[i].getPassenger().getLastName() + "-" + cabinsReference[i].getOccupants() + "-" + cabinsReference[i].getPassenger().getExpenses() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully saved");
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
    }

    public static void loadFile(Cabin[] cabinsReference) {
        try {
            File myObject = new File("src/filename.txt");
            Scanner myReader = new Scanner(myObject);
            int i = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] values = data.split("-");

                Passenger passenger = new Passenger(values[1], values[2], Double.parseDouble(values[4]));
                cabinsReference[i].setOccupants(Integer.parseInt(values[3]));
                cabinsReference[i].setPassenger(passenger);
                i++;
            }
            myReader.close();
            System.out.println("Successfully loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File can't be found");
        }
    }

    public static void showExpenses(Cabin[] cabinsReference) {
        try {
            double total = 0;
            for (int i = 1; i < 13; i++) {
                if (cabinsReference[i].passenger.getFirstName().equals("")) {
                    System.out.println("Cabin " + i + " is empty and has no expenses");
                } else {
                    System.out.println("Cabin " + i + " taken by " + cabinsReference[i].getPassenger().getFirstName() + " " + cabinsReference[i].getPassenger().getLastName() + " has expenses of £" + cabinsReference[i].getPassenger().getExpenses());
                    total = total + cabinsReference[i].getPassenger().getExpenses(); //Calculates total for cabins which are occupied
                }
            }
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Would you like to view the total expenses of all passengers? 'y' for yes, 'x' to go back. ");
                String input = scanner.next();

                if (input.equalsIgnoreCase("y")) {
                    System.out.println("Total expenses: £" + total);
                    break;
                } else if (input.equalsIgnoreCase("x")) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again with 'y' or 'x'");
        }
    }
}

//References
//https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
//w3schools.com/java/java_files_create.asp
//https://www.w3schools.com/java/java_files_read.asp
//https://www.journaldev.com/709/java-read-file-line-by-line
//https://www.edureka.co/blog/shallow-and-deep-copy-java/
//https://docstore.mik.ua/orelly/java-ent/jnut/ch03_02.htm


