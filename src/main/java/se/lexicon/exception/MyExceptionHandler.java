package se.lexicon.exception;

public class MyExceptionHandler {

    public static void handleExceptions(Exception e){
        if(e instanceof IllegalArgumentException){
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } else if(e instanceof InsufficientFoundsException){
            System.out.println("InsufficientFoundsException: " + e.getMessage());
        } else {
            System.out.println("## unexpected error ##");
            e.printStackTrace();
        }

    }
}
