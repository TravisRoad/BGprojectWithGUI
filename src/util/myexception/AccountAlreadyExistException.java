package util.myexception;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String msg) {
        super(msg);
    }
}
