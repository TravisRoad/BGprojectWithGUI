package util.myexception;

public class AccountNotExistException extends Exception{
    public AccountNotExistException(){
        super();
    }
    public AccountNotExistException(String msg){
        super(msg);
    }
}
