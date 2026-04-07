package bankingapp.service;

import bankingapp.model.Account;
import bankingapp.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankService {
    private String lastError;

    public boolean deposit(Account account, double amount) {
        return deposit(account, BigDecimal.valueOf(amount));
    }

    public boolean deposit(Account account, BigDecimal amount) {
        lastError = null;

        if (!isValidAccount(account) || !isValidAmount(amount)) {
            return false;
        }

        account.deposit(amount);
        account.addTransaction(new Transaction("Deposit", amount, LocalDateTime.now(), "Deposit to " + account.getAccountType(), "Completed"));
        return true;
    }

    public boolean withdraw(Account account, double amount) {
        return withdraw(account, BigDecimal.valueOf(amount));
    }

    public boolean withdraw(Account account, BigDecimal amount) {
        lastError = null;

        if (!isValidAccount(account) || !isValidAmount(amount)) {
            return false;
        }

        if (account.getBalance().compareTo(amount) < 0) {
            lastError = "Insufficient funds.";
            account.addTransaction(new Transaction("Withdraw", amount, LocalDateTime.now(), "Withdrawal from " + account.getAccountType(), "Failed"));
            return false;
        }

        account.withdraw(amount);
        account.addTransaction(new Transaction("Withdraw", amount, LocalDateTime.now(), "Withdrawal from " + account.getAccountType(), "Completed"));
        return true;
    }

    public boolean transfer(Account source, Account destination, double amount) {
        return transfer(source, destination, BigDecimal.valueOf(amount));
    }

    public boolean transfer(Account source, Account destination, BigDecimal amount) {
        lastError = null;

        if (!isValidAccount(source) || !isValidAccount(destination) || !isValidAmount(amount)) {
            return false;
        }

        if (source == destination || source.getAccountNumber().equals(destination.getAccountNumber())) {
            lastError = "You cannot transfer to the same account.";
            return false;
        }

        if (source.getBalance().compareTo(amount) < 0) {
            lastError = "Insufficient funds.";
            source.addTransaction(new Transaction("Transfer", amount, LocalDateTime.now(), "Transfer to " + destination.getAccountType(), "Failed"));
            return false;
        }

        source.withdraw(amount);
        destination.deposit(amount);
        source.addTransaction(new Transaction("Transfer", amount, LocalDateTime.now(), "Transfer to " + destination.getAccountType(), "Completed"));
        destination.addTransaction(new Transaction("Transfer", amount, LocalDateTime.now(), "Transfer from " + source.getAccountType(), "Completed"));
        return true;
    }

    public String getLastError() {
        return lastError;
    }

    private boolean isValidAccount(Account account) {
        if (account == null) {
            lastError = "Account is required.";
            return false;
        }

        return true;
    }

    private boolean isValidAmount(BigDecimal amount) {
        if (amount == null) {
            lastError = "Please enter a valid amount.";
            return false;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            lastError = "Amount must be greater than zero.";
            return false;
        }

        return true;
    }
}
