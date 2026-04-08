import java.time.LocalDateTime;

public class Transaction {
    public static final String TYPE_DEPOSIT = "deposit";
    public static final String TYPE_WITHDRAWAL = "withdrawal";


    private int id;
    private int accountId;
    private double amount;
    private String type;
    private String description;
    private LocalDateTime createdAt;

    public Transaction(int id, int accountId, double amount, String type, String description) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


}
