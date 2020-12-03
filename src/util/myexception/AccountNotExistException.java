package util.myexception;

/**
 * 账户不存在
 */
public class AccountNotExistException extends Exception{
    public AccountNotExistException(){
        super();
    }

    public AccountNotExistException(String msg){
        super(msg);
    }
}
