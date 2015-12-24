package beans;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NitefullWind
 */
public class SignInBean {  
    private String name;
    private String password;
    private String errMessage;
    private UserBean user;
    public OpDB db;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the errMessage
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * @param errMessage the errMessage to set
     */
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
    
    public String verify(){
        //传入用户名密码，查询数据库
        List parameterList = new ArrayList();
        parameterList.add(name);
        parameterList.add(password);
        List list = getDb().execSelect("select id from tbl_users where (name=? and password=?)", parameterList);
        
        if(list.size() > 0) {
            //得到第一行的list
            list = (List)list.get(0);
            //得到行中的id
            String userId = (String)list.get(0);

            if(userId != null){
                user.setId(userId);
                user.setName(name);
                return "success";
            }
        }
        errMessage = "用户名或密码错误";
        return "failure";
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    public OpDB getDb() {
        return db;
    }

    public void setDb(OpDB db) {
        this.db = db;
    }
}
