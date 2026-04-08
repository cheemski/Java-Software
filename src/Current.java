public class Current extends Account {
    private double overdraftLimit;

    public Current(int id, int userId, String accountNumber, double balance, double overdraftLimit) {
        super(id, userId, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
            double newBalance = getBalance() - amount;
            if (newBalance < 0) {
                newBalance = 0; // Set balance to zero if it goes negative
            }
            // Update the balance using reflection or by adding a method in Account to set balance
            try {
                java.lang.reflect.Field balanceField = Account.class.getDeclaredField("balance");
                balanceField.setAccessible(true);
                balanceField.set(this, newBalance);
                return true;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Current{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", accountNumber='" + getAccountNumber() + '\'' +
                ", balance=" + getBalance() +
                ", createdAt=" + getCreatedAt() +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }
    
}
