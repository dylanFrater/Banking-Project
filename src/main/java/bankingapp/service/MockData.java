package bankingapp.service;

import bankingapp.model.Account;
import bankingapp.model.Transaction;
import bankingapp.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<User> createUsers() {
        List<User> users = new ArrayList<>();

        User student = new User("student", "password123", "Student User");
        Account checking = new Account("CHK-1001", "Checking", new BigDecimal("500.00"));
        Account savings = new Account("SAV-1001", "Savings", new BigDecimal("1500.00"));

        checking.addTransaction(new Transaction("Deposit", new BigDecimal("500.00"), LocalDateTime.now().minusDays(5), "Opening deposit", "Completed"));
        savings.addTransaction(new Transaction("Deposit", new BigDecimal("1500.00"), LocalDateTime.now().minusDays(5), "Opening deposit", "Completed"));

        student.addAccount(checking);
        student.addAccount(savings);
        users.add(student);

        return users;
    }
}
