import java.io.*;
import java.util.*;

public class cruiseShip {
    public static void main(String[] args) {
        String menu;
        String[] cabins = new String[13];
        String[] passengersLastName = new String[13];
        Scanner scanner = new Scanner(System.in);

        initialiser(cabins, passengersLastName);

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
            System.out.println("Enter Option: ");
            menu = scanner.nextLine().toUpperCase();

            switch (menu) {  //Switch is used for the main navigation
                case ("A"):
                    addPassengers(cabins, passengersLastName);
                    break;

                case ("E"):
                    emptyCabins(cabins);
                    break;

                case ("V"):
                    viewCabins(cabins, passengersLastName);
                    break;

                case ("D"):
                    deletePassengers(cabins, passengersLastName);
                    break;

                case ("F"):
                    findPassengers(cabins);
                    break;

                case ("S"):
                    saveFile(cabins,passengersLastName);
                    break;

                case ("L"):
                    loadFile(cabins,passengersLastName);
                    break;

                case ("O"):
                    sortPassengers(cabins);
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

    public static void initialiser(String[] cabinsReference, String[] customerName) {
        for (int i = 1; i < 13; i++) {
            cabinsReference[i] = "";
            customerName[i] = ""; //initialises arrays as empty
        }
    }

    public static void addPassengers(String[] cabinsReference, String[] customerName) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int cabinID;
            try {
                System.out.println("Choose cabin number 1-12 or 13 to Stop:");
                cabinID = scanner.nextInt();
                if (cabinID < 13 && cabinID > 0) {
                    System.out.println("Enter Passengers' First Name for Cabin " + cabinID + " :");
                    String cabinFirstName = scanner.next();
                    System.out.println("Enter Passengers' Surname for Cabin " + cabinID + " :");
                    String cabinLastName = scanner.next();

                      if (HasNoNumbers(cabinFirstName) && HasNoNumbers(cabinLastName)) { //runs through HasNoNumbers to ensure theres no illegal characters
                        cabinsReference[cabinID] = cabinFirstName;
                        customerName[cabinID] = cabinLastName; //assigning variables to chosen cabin
                        System.out.println("Passenger " + cabinFirstName + " added in Cabin " + cabinID + " Successfully");
                    } else
                          System.out.println("Letters only. Try again");

                } else if (cabinID > 13 || cabinID <= 0) {
                    System.out.println("Out of range input. Please choose from 1-12");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input, try again");
            }
        }
    }

    private static boolean HasNoNumbers(String value){
        String[] Numbers = {"0", "1", "2", "3" , "4","5","6","7","8","9",".","%","!","@","£","$","^","&","*","(",")","{","}",",","|",">","<",";",":","-","+","~","±","§","_","="};
        for (String i : Numbers)
            if(value.contains(i))
                return false;
        return true; //a non-regex solution to check for illegal characters in first and last name
    }

    public static void emptyCabins(String[] cabinsReference) {
        System.out.println("\n");
        System.out.println("Empty Cabins: ");

        for (int i = 1; i < 13; i++) {
            if (cabinsReference[i].equals("")) { //Prints only the empty cabins, if some are not empty, they will not be displayed
                System.out.println("Cabin " + i + " is Empty");
            }
        }
    }

    public static void viewCabins(String[] cabinsReference, String[] customerName) {
        System.out.println("\n");
        System.out.println("All Cabins");


        for (int i = 1; i < 13; i++) { //Loops around all cabins and prints either as empty or with first and last name
            if (cabinsReference[i].equals("")) {
                System.out.println("Cabin " + i + " is Empty");
            } else
            System.out.println("Cabin " + i + " taken by " + cabinsReference[i] + " " + customerName[i]);
        }
        }

    public static void sortPassengers(String[] cabinsReference) {
        String[] occupants = new String[cabinsReference.length];

        System.out.println("\n");
        System.out.println("Passengers alphabetically sorted: ");

        System.arraycopy(cabinsReference, 0, occupants, 0, cabinsReference.length);

        for(int i = 1; i< occupants.length-1; i++) { //a nested for loop is used to sort passengers
            for (int j = i+1; j<occupants.length; j++) {
                if(occupants[i].compareTo(occupants[j]) > 0) {
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


    public static void deletePassengers(String[] cabinsReference, String[] customerName) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int cabinID;
            try {
                System.out.println("Choose cabin number 1-12 to delete or 13 to Stop:");
                cabinID = scanner.nextInt();

                if (cabinID < 13 && cabinID > 0) {
                    System.out.println("Enter First Name at " + cabinID + " to confirm the deletion:");
                    String cabinPassengerName = scanner.next();

                    if (cabinPassengerName.equalsIgnoreCase(cabinsReference[cabinID])) {
                        cabinsReference[cabinID] = "";
                        customerName[cabinID] = ""; //sets fields first & last name to empty in specified cabinID
                        System.out.println("\n");
                        System.out.println("Passenger " + cabinPassengerName + " at Cabin " + cabinID + " deleted successfully.");
                    } else {
                        System.out.println("Passenger Name Invalid");
                    }
                } else if (cabinID > 13 || cabinID < 0) {
                    System.out.println("Out of range input. Try 1-12 ");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. Try again with 1-12");
            }
        }
    }

    public static void findPassengers(String[] cabinsReference) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int index;
            System.out.println("Enter First Name to find passenger or 'x' to stop: ");
            String cabinPassengerName = scanner.next();

            if (!(cabinPassengerName.equalsIgnoreCase("x"))) {
                for (index = 1; index < cabinsReference.length; index++) {
                    if (cabinsReference[index].equals(cabinPassengerName)) {
                        System.out.println(cabinPassengerName + " is located at Cabin " + index);
                        break;
                    }
                    else if (cabinsReference.length-1 == index) {
                        System.out.println("Invalid passenger name. Try again.");
                    }
                }
            }
            else {
                break;
            }
        }
    }

    public static void saveFile(String[] cabinsReference,String[] customerName) { //saving and loading references are available
        try {
            File myObject = new File("src/filename.txt");
            if (myObject.createNewFile()) {
                System.out.println("File created: " + myObject.getName());
            }
            else {
                System.out.println("File is already created");
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
        try {
            FileWriter myWriter = new FileWriter("src/filename.txt");
            for (int i = 1; i < 13; i++) {
                myWriter.write(i + "-" + cabinsReference[i] + "-" + customerName[i] + "\n");
            }
            myWriter.close();
            System.out.println("Successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void loadFile(String[] cabinsReference,String[] customerName) {
        try {
            File myObject = new File("src/filename.txt");
            Scanner myReader = new Scanner(myObject);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] values = data.split("-");

                int count=0;
                for (int i=0; i < (values.length/3); i+=3) {
                    cabinsReference[Integer.parseInt(values[count])] = values[i+1];
                    customerName[Integer.parseInt(values[count])] = values[i+2];
                    count++;
                }
            }
            myReader.close();
            System.out.println("Successfully loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File can't be found");
        }
    }
}


//References
//https://www.geeksforgeeks.org/java-program-to-sort-names-in-an-alphabetical-order/
//w3schools.com/java/java_files_create.asp
//https://www.w3schools.com/java/java_files_read.asp
//https://www.journaldev.com/709/java-read-file-line-by-line
//https://www.baeldung.com/java-array-copy