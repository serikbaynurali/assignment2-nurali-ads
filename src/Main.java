import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        LinkedList<BankAccount> accounts = new LinkedList<>();
        Stack<String> transactionHistory = new Stack<>();
        Queue<String> billQueue = new LinkedList<>();
        Queue<BankAccount> accountRequests = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        BankAccount[] Accounts = new BankAccount[3];


        Accounts[0] = new BankAccount(101, "Ali", 150000);
        Accounts[1] = new BankAccount(102, "Sara", 220000);
        Accounts[2] = new BankAccount(103, "John", 180000);


        System.out.println("Accounts List:");

        for (int i = 0; i < Accounts.length; i++) {
            System.out.println((i + 1) + ". " + Accounts[i]);
        }

        while (true) {
            System.out.println("1. Add Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Search by Username");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.println("7. Add Transaction");
            System.out.println("8. Undo Last Transaction");
            System.out.println("9. Show Last Transaction");
            System.out.println("10. Add Bill Payment");
            System.out.println("11. Process Next Bill");
            System.out.println("12. Display Bill Queue");
            System.out.println("13. Request New Account");
            System.out.println("14. Process Account Request (Admin)");
            System.out.println("15. Show Pending Requests");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter account number: ");
                    int accNum = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter balance: ");
                    double balance = scanner.nextDouble();

                    accounts.add(new BankAccount(accNum, username, balance));
                    System.out.println("Account added successfully");
                    break;

                case 2:
                    System.out.println("Accounts List:");
                    int index = 1;
                    for (BankAccount acc : accounts) {
                        System.out.println(index + ". " + acc.toString());
                        index++;
                    }
                    break;

                case 3:
                    System.out.print("Enter username to search: ");
                    String searchName = scanner.nextLine();
                    boolean found = false;

                    for (BankAccount acc : accounts) {
                        if (acc.getUsername().equalsIgnoreCase(searchName)) {
                            System.out.println("Found: " + acc);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Account not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter username to deposit: ");
                    String depositName = scanner.nextLine();
                    boolean depFound = false;
                    for (BankAccount acc : accounts) {
                        if (acc.getUsername().equalsIgnoreCase(depositName)) {
                            System.out.println("Deposit: " );
                            double amount = scanner.nextDouble();
                            depFound = true;
                            acc.deposit(amount);
                            System.out.println("New Balance: " + acc.getBalance());
                            break;

                        }

                        if (!depFound) {
                            System.out.println("Account not found");
                        }

                    }
                    break;
                case 5:
                    System.out.print("Enter username to withdraw: ");
                    String withdrawName = scanner.nextLine();
                    boolean withdrawFound = false;
                    for (BankAccount acc : accounts) {
                        if (acc.getUsername().equalsIgnoreCase(withdrawName)) {
                            System.out.println("Enter withdrawal amount: " );
                            double amount = scanner.nextDouble();
                            withdrawFound = true;
                            System.out.println(acc.withdraw(amount));
                            break;

                        }

                        if (!withdrawFound) {
                            System.out.println("Account not found");
                        }

                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    return;
                case 7:
                    System.out.println("Choose transaction type:");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Bill Payment");

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter username: ");
                    String user = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();

                    String record = "";

                    if (type == 1) {
                        record = "Deposit " + amount + " to " + user;
                    } else if (type == 2) {
                        record = "Withdraw " + amount + " from " + user;
                    } else if (type == 3) {
                        record = "Bill Payment " + amount + " by " + user;
                    }

                    transactionHistory.push(record);
                    System.out.println("Transaction added: " + record);
                    break;
                case 8:
                    if (!transactionHistory.isEmpty()) {
                        String removed = transactionHistory.pop();
                        System.out.println("Undo → " + removed + " removed");
                    } else {
                        System.out.println("No transactions to undo");
                    }
                    break;
                case 9:
                    if (!transactionHistory.isEmpty()) {
                        System.out.println("Last transaction: " + transactionHistory.peek());
                    } else {
                        System.out.println("No transactions available");
                    }
                    break;
                case 10:
                    System.out.print("Enter bill name: ");
                    String bill = scanner.nextLine();

                    billQueue.add(bill);
                    System.out.println("Added: " + bill);
                    break;
                case 11:
                    if (!billQueue.isEmpty()) {
                        String processed = billQueue.poll();
                        System.out.println("Processing: " + processed);
                    } else {
                        System.out.println("No bills in queue");
                    }
                    break;
                case 12:
                    if (billQueue.isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Remaining bills:");
                        for (String b : billQueue) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 13:
                    System.out.print("Enter account number: ");
                    int accnum = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter username: ");
                    String Username = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    double Balance = scanner.nextDouble();

                    accountRequests.add(new BankAccount(accnum, Username, Balance));
                    System.out.println("Account request submitted");
                    break;
                case 14:
                    if (!accountRequests.isEmpty()) {
                        BankAccount approved = accountRequests.poll();
                        accounts.add(approved);

                        System.out.println("Account approved for: " + approved.getUsername());
                    } else {
                        System.out.println("No pending requests");
                    }
                    break;
                case 15:
                    if (accountRequests.isEmpty()) {
                        System.out.println("No pending requests");
                    } else {
                        System.out.println("Pending requests:");
                        for (BankAccount acc : accountRequests) {
                            System.out.println(acc.toString());
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}