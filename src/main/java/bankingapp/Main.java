package bankingapp;

import bankingapp.model.Account;
import bankingapp.model.User;
import bankingapp.service.AuthService;
import bankingapp.service.BankService;

public class Main {
    public static void main(String[] args) {
        //testing for git

        AuthService authService = new AuthService();
        BankService bankService = new BankService();

        User user = authService.login("student", "password123");

        if (user != null) {
            Account checking = user.getAccounts().get(0);
            Account savings = user.getAccounts().get(1);

            bankService.deposit(checking, 100);
            bankService.withdraw(checking, 50);
            bankService.transfer(checking, savings, 25);

            System.out.println("Logged in as: " + user.getFullName());
            System.out.println("Checking balance: $" + checking.getBalance());
            System.out.println("Savings balance: $" + savings.getBalance());
            System.out.println("Checking transactions: " + checking.getTransactions().size());
            System.out.println("Savings transactions: " + savings.getTransactions().size());
        } else {
            System.out.println(authService.getLastError());
        }
    }
}
