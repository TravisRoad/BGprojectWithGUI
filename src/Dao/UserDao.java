package Dao;

import model.User;
import util.Database;
import util.Error;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * user table database access object
 */
public class UserDao {
    public UserDao(){
        //init
    }

    public boolean login(User user){
        return true;
    }

    public Error signup(User user){
        boolean isExist = isDuplicated(user);
        if(isExist) return Error.isExist;
        else //TODO

        return Error.success;
    }

    /**
     * 判断是否有对应的用户
     * @param user 用户
     * @return result:boolean
     */
    private boolean isDuplicated(User user){
        String sql = "SELECT * FROM usrs WHERE username=?";
        boolean flag = false;
        try(PreparedStatement ps = Database.getConn().prepareStatement(sql)){
            ps.setObject(1,user.getUserName());
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) flag = false;
                else flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
