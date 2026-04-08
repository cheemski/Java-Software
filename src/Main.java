import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static DatabaseManager db;
    private static AuthService authService;
    private static Session session;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        db = new DatabaseManager();
        authService = new AuthService(db);
        session = new Session();
    }


    private static void showMainMenu() {
    System.out.println("\n--- Digital Banking Platform ---");
    System.out.println("1. Login");
    System.out.println("2. Register as new customer");
    System.out.println("3. Exit");
    System.out.print("Choice: ");
    String input = scanner.nextLine().trim();
    switch (input) {
        case "1" -> doLogin();
        case "2" -> doRegister();
        case "3" -> {
            System.out.println("Goodbye.");
            System.exit(0);
        }
        default -> System.out.println("Invalid choice.");
        }
    }

    private static void doLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter IC number or email: ");
        String idOrEmail = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        User user = idOrEmail.contains("@") ? authService.loginByEmail(idOrEmail, password) : authService.loginByIc(idOrEmail, password);
        if (user != null) {
            session.setCurrentUser(user);
            System.out.println("Welcome, " + user.getName() + (user.isAdmin() ? " (Admin)" : "") + ".");
        } else {
            System.out.println("Invalid IC/email or password.");
        }
    }

    private static void doRegister() {
        System.out.println("\n--- New Customer Registration ---");
        System.out.print("Full name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Date of birth (YYYY-MM-DD): ");
        LocalDate dob;
        try {
            dob = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid date.");
            return;
        }
        System.out.print("IC number: ");
        String ic = scanner.nextLine().trim();
        System.out.print("Occupation: ");
        String occupation = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Account type (savings/current): ");
        String accountType = scanner.nextLine().trim().toLowerCase();
        if (!Account.TYPE_SAVINGS.equals(accountType) && !Account.TYPE_CURRENT.equals(accountType)) {
            System.out.println("Account type must be 'savings' or 'current'.");
            return;
        }
        System.out.print("Initial deposit amount: ");
        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(scanner.nextLine().trim());
            if (initialDeposit < 0) initialDeposit = 0;
        } catch (NumberFormatException e) {
            initialDeposit = 0;
        }
        User created = customerService.register(name, address, dob, ic, occupation, email, phone, password, accountType, initialDeposit);
        if (created != null) {
            System.out.println("Registration successful. Your Customer ID is linked to your account. Please wait for admin approval of your account.");
        } else {
            System.out.println("Registration failed (IC or email may already exist).");
        }
    }
}
