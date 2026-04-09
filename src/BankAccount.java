public class BankAccount {
    private int accountNumber;
    private String username;
    private double balance;

    public BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance-= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return username + " – Balance: " + balance;
    }
}