public class Savings extends Account {
    private double interestRate;

    public Savings(int id, int userId, String accountNumber, double balance, double interestRate) {
        super(id, userId, accountNumber, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
    }

    @Override
    public String toString() {
        return "Savings{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", accountNumber='" + getAccountNumber() + '\'' +
                ", balance=" + getBalance() +
                ", createdAt=" + getCreatedAt() +
                ", interestRate=" + interestRate +
                '}';
    }
    
}
