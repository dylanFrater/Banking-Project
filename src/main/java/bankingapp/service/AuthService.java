package bankingapp.service;

import bankingapp.model.User;

import java.util.List;

public class AuthService {
    private final List<User> users;
    private String lastError;

    public AuthService() {
        this.users = MockData.createUsers();
    }

    public User login(String username, String password) {
        lastError = null;

        if (isBlank(username) || isBlank(password)) {
            lastError = "Username and password are required.";
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.passwordMatches(password)) {
                return user;
            }
        }

        lastError = "Invalid username or password.";
        return null;
    }

    public String getLastError() {
        return lastError;
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
