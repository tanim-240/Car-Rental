
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String Brand;
    private String Model;
    private double bestPricePerDay;
    private boolean isAvailAble;

    public Car(String carId,String Brand, String Model, Double bestPricePerDay) {
        this.carId = carId;
        this.Brand = Brand;
        this.Model = Model;
        this.bestPricePerDay = bestPricePerDay;
        this.isAvailAble = true;
    }
    public String getCar_Id() {
        return carId;
    }
    public String getBrand() {
        return Brand;
    }
    public String getModel() {
        return Model;
    }

    public double calculatePrice(int rentalDays) {
        return bestPricePerDay * rentalDays;
    }

    public boolean isAvailAble() {
        return isAvailAble;
    }

    public void Rent() {
        isAvailAble = false;
    }

    public void ReturnCar() {
        isAvailAble = true;
    }
}

class Customer {
    private String customerId;
    private String customer_name;


    public Customer(String customerId, String customer_name) {
        this.customerId = customerId;
        this.customer_name = customer_name;

    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

}

class Rental {

    private Car car;
    private Customer customer;

    private int rentDays;

    public Rental(Car car, Customer customer, int rentDays) {
        this.car = car;
        this.customer = customer;
        this.rentDays = rentDays;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }
}

class CarRentalSystem {

    private List<Car> Cars;
    private List<Customer> Customers;
    private List<Rental> rentals;

   public CarRentalSystem() {
        Cars = new ArrayList<>();
        Customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        Cars.add(car);
    }

    public void addCustomer(Customer customer) {
        Customers.add(customer);
    }

    public void getRent(Car car, Customer customer, int days) {
        if (car.isAvailAble()) {
            car.Rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("car is not available!");
        }
    }

    public void returnCar(Car car) {

        car.ReturnCar();

        Rental removeRent = null;

        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                removeRent = rental;
                break;
            }
        }
        if (removeRent != null) {
            rentals.remove(removeRent);
        } else {
            System.out.println("car was not rented! ");
        }
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("which option do you want to take->");

            System.out.println("1.Rent Car");
            System.out.println("2.Return Car");
            System.out.println("3.Exit");

            System.out.print("Enter Your choice : ");
            int choice = sc.nextInt();

            sc.nextLine();

            if (choice == 1) {
                System.out.println("//==Rent a Car==//");
                System.out.println();
                System.out.println("Hello sir!");
                System.out.print("Enter your name please--> ");
                String CustomerName = sc.nextLine();

                System.out.println("\nHere are some available cars...\n");
                for (Car car : Cars) {
                    if (car.isAvailAble()) {
                        System.out.println(car.getCar_Id() + " - " + car.getBrand() + "-" + car.getModel());
                    }
                }

                System.out.print("\nEnter the Car id : ");
                String carId = sc.next();


                System.out.print("\nEnter the number of days for rental : ");
                int days = sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CTR" + (Customers.size() + 1), CustomerName);
                addCustomer(newCustomer);


                Car selectedCar = null;
                for (Car car : Cars) {
                    if (car.getCar_Id().equals(carId) && car.isAvailAble()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {

                    double priceOfCarRent = selectedCar.calculatePrice(days);
                    System.out.println();
                    System.out.println("Rental information : ");
                    System.out.println("Customer Id :" + newCustomer.getCustomerId());
                    System.out.println("Customer Name :" + newCustomer.getCustomer_name());
                    System.out.println("Car :" + selectedCar.getBrand() + selectedCar.getModel());
                    System.out.println("carId :" + carId);
                    System.out.println("Rental days :" + days);
                    System.out.printf("Total price : $%.2f", priceOfCarRent);

                    System.out.println("\ndo you want to confirm car rent (y/n) :");
                    String confirmationWord = sc.nextLine();

                    if (confirmationWord.equalsIgnoreCase("y")) {
                        getRent(selectedCar, newCustomer, days);
                        System.out.println("Rented successfully!");
                    } else {
                        System.out.println("Car rent canceled!");
                    }
                }
                else {
                    System.out.println("invalid id or car is not available for rent.....");
                }
            } else if (choice == 2) {
                System.out.println(" //--Return car--//:");
                System.out.print("Enter the car id : ");
                String carId = sc.nextLine();

                Car carReturn = null;

                for (Car car : Cars) {
                    if (car.getCar_Id().equals(carId) ) {
                        carReturn = car;
                        break;
                    }
                }

                if (carReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar()==(carReturn)) {
                            customer=rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null){
                        returnCar(carReturn);
                        System.out.println("car returned successfully");
                    }else {
                        System.out.println("Car was not rented or information invalid ");
                    }
                }else {
                    System.out.println("Invalid information or car is not rented!...");
                }
            } else if (choice == 3) {
                System.out.println("Thank you for your cooperation...");
              //  break;
            }else {
                System.out.println("Invalid choice!");
            }
        }
    }

}

public class Car_rental {
    public static void main(String[] args) {

        CarRentalSystem rentCar = new CarRentalSystem();

        Car car1 = new Car( "AR1","Audi","19s", 35.0);
        Car car2 = new Car( "AR2", "porsche","18s",70.0);
        Car car3 = new Car( "AR3","premio", "19s", 20.0);

        rentCar.addCar(car1);
        rentCar.addCar(car2);
        rentCar.addCar(car3);

        rentCar.menu();

    }
}










