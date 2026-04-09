import java.util.*;

public class task6 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();
    static Queue<String> billQueue = new LinkedList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
            System.out.println("4 – Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: bankMenu(); break;
                case 2: atmMenu(); break;
                case 3: adminMenu(); break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    // 🔹 BANK MENU
    static void bankMenu() {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1. Request Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Username: ");
                String user = scanner.nextLine();

                accountRequests.add(new BankAccount(0, user, 0));
                System.out.println("Request submitted");
                break;

            case 2:
                System.out.print("Username: ");
                String depUser = scanner.nextLine();

                for (BankAccount acc : accounts) {
                    if (acc.getUsername().equalsIgnoreCase(depUser)) {
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();

                        acc.deposit(amount);
                        history.push("Deposit " + amount + " to " + depUser);

                        System.out.println("New balance: " + acc.getBalance());
                        return;
                    }
                }
                System.out.println("Account not found");
                break;

            case 3:
                System.out.print("Username: ");
                String withUser = scanner.nextLine();

                for (BankAccount acc : accounts) {
                    if (acc.getUsername().equalsIgnoreCase(withUser)) {
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();

                        if (acc.withdraw(amount)) {
                            history.push("Withdraw " + amount + " from " + withUser);
                            System.out.println("New balance: " + acc.getBalance());
                        } else {
                            System.out.println("Insufficient funds");
                        }
                        return;
                    }
                }
                System.out.println("Account not found");
                break;
        }
    }

    // 🔹 ATM MENU
    static void atmMenu() {
        System.out.println("\n--- ATM MENU ---");
        System.out.println("1. Balance");
        System.out.println("2. Withdraw");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Username: ");
        String user = scanner.nextLine();

        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(user)) {

                if (choice == 1) {
                    System.out.println("Balance: " + acc.getBalance());
                } else if (choice == 2) {
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();

                    if (acc.withdraw(amount)) {
                        history.push("ATM Withdraw " + amount + " from " + user);
                        System.out.println("Take your cash");
                    } else {
                        System.out.println("Insufficient funds");
                    }
                }
                return;
            }
        }

        System.out.println("Account not found");
    }

    // 🔹 ADMIN MENU
    static void adminMenu() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. Process Account Requests");
        System.out.println("2. View Bill Queue");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                if (!accountRequests.isEmpty()) {
                    BankAccount acc = accountRequests.poll();
                    accounts.add(acc);

                    System.out.println("Approved: " + acc.getUsername());
                } else {
                    System.out.println("No requests");
                }
                break;

            case 2:
                if (billQueue.isEmpty()) {
                    System.out.println("No bills");
                } else {
                    for (String b : billQueue) {
                        System.out.println(b);
                    }
                }
                break;
        }
    }
}
