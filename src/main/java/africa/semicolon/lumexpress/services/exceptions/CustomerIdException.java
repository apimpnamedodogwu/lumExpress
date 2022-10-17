package africa.semicolon.lumexpress.services.exceptions;

public class CustomerIdException extends Exception{
    public CustomerIdException (Long id) {
        super(String.format("Customer with id number " + id + " does not exist"));
    }
}
