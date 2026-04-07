package bankingapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionType;
    private final BigDecimal amount;
    private final LocalDateTime dateTime;
    private final String description;
    private final String status;

    public Transaction(String transactionType, BigDecimal amount, LocalDateTime dateTime, String description, String status) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateTime = dateTime;
        this.description = description;
        this.status = status;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
