import java.util.*;

public class SimpleBankingSystem {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Simple Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> displayAccount();
                case 5 -> {
                    System.out.println("Thanks for banking with us. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        String accNumber = UUID.randomUUID().toString().substring(0, 8);
        accounts.put(accNumber, new Account(name, 0));
        System.out.println("Account created successfully. Account Number: " + accNumber);
    }

    private static void deposit() {
        Account acc = getAccount();
        if (acc == null) return;
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine());
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        acc.balance += amount;
        System.out.println("Deposited ₹" + amount);
    }

    private static void withdraw() {
        Account acc = getAccount();
        if (acc == null) return;
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine());
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (acc.balance < amount) {
            System.out.println("Insufficient balance.");
            return;
        }
        acc.balance -= amount;
        System.out.println("Withdrawn ₹" + amount);
    }

    private static void displayAccount() {
        Account acc = getAccount();
        if (acc == null) return;
        System.out.println("Account Holder: " + acc.name);
        System.out.println("Balance: ₹" + acc.balance);
    }

    private static Account getAccount() {
        System.out.print("Enter account number: ");
        String accNumber = sc.nextLine();
        Account acc = accounts.get(accNumber);
        if (acc == null) {
            System.out.println("Account not found.");
        }
        return acc;
    }

    static class Account {
        String name;
        double balance;

        Account(String name, double balance) {
            this.name = name;
            this.balance = balance;
        }
    }
}

