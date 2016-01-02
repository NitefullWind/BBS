/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NitefullWind
 */
public class SignUpBean {
    private String name = null;         //用户名
    private String password = null;     //密码
    private String rePassword = null;   //确认密码
    private String email = null;        //邮箱
    private String phone = null;        //电话
    private OpDB db;
    
    public String commit(){
        if(!name.isEmpty() && !password.isEmpty() && (password.equals(rePassword))){
            List parameterList = new ArrayList();
            parameterList.add(name);
            parameterList.add(password);
            parameterList.add(email);
            parameterList.add(phone);
            int c = getDb().execUpdate("insert into tbl_users (name,password,email,phone) values (?,?,?,?)", parameterList);
            if(c > 0) {
                return "success";
            }
        }
        return "failure";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OpDB getDb() {
        return db;
    }

    public void setDb(OpDB db) {
        this.db = db;
    }
    
}
