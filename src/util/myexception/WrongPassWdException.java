package util.myexception;

/**
 * 密码错误
 */
public class WrongPassWdException extends Exception{
    public WrongPassWdException(){
        super();
    }

    public WrongPassWdException(String msg){
        super(msg);
    }
}
