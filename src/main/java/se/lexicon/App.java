package se.lexicon;

import se.lexicon.exception.InsufficientFoundsException;
import se.lexicon.exception.MyExceptionHandler;

public class App {
    public static void main(String[] args) {
        try {
            BankAccount account1 = new BankAccount(1000, 100.00);
            System.out.println(account1);
            account1.deposit(50.00);
            System.out.println(account1);
            //account1.deposit(-250.00);
            account1.withdraw(500);
            System.out.println(account1);
        } catch (Exception e){
            MyExceptionHandler.handleExceptions(e);
        }



    }
}
