package util.myexception;

public class WrongPassWdException extends Exception{
    public WrongPassWdException(){
        super();
    }
    public WrongPassWdException(String msg){
        super(msg);
    }
}
