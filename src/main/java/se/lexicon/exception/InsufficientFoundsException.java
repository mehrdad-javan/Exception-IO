package se.lexicon.exception;

public class InsufficientFoundsException extends Exception {

    private double balance;
    private double amount;

    public InsufficientFoundsException(String message, double balance, double amount) {
        super(message);
        this.balance = balance;
        this.amount = amount;
    }
    public InsufficientFoundsException(String message) {
        super(message);
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }
}
