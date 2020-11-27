package dao;

import model.User;
import util.Database;
import util.myexception.AccountNotExistException;
import util.myexception.WrongPassWdException;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * user table database access object
 * 访问User表
 */
public class UserDao {
    /**
     * 插入新用户，用户id会自动生成
     * @param userName 用户名
     * @param passwd 密码
     * @return 插入成功
     */
    public boolean insert(String userName, String passwd){
        String sql = "INSERT INTO user (username, passwd) VALUES (?,?)";
        boolean ret = false;
        try(PreparedStatement ps = Database.getConn().prepareStatement(sql)){
            ps.setObject(1,userName);
            ps.setObject(2,passwd);
            ret = ps.execute();
            ret = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 查询是否有对应的用户
     * @param userName 用户名
     * @param passwd 密码
     * @throws SQLException, AccountNotExistException, WrongPassWdException
     * @return User 查询到的用户对象，失败则返回{@code null}
     */
    public User search(String userName, String passwd) throws SQLException, AccountNotExistException, WrongPassWdException {
        String sql = "SELECT * FROM user WHERE username=?";
        User user = null;
        try(PreparedStatement ps = Database.getConn().prepareStatement(sql)){
            ps.setObject(1,userName);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){//查询成功
                    long id = rs.getLong(1);
                    if(!rs.getString(3).equals(passwd)){
                        throw new WrongPassWdException();
                    }
                    else{
                        user = new User(userName,passwd,id);
                    }
                }
                else{//无对应账户
                    throw new AccountNotExistException();
                }
            }
        }
        return user;
    }
}
