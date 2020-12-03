package util.myexception;

/**
 * 账户已经存在
 */
public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String msg) {
        super(msg);
    }
}
