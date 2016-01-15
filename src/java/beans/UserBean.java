/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author NitefullWind
 */
public class UserBean {
    private String id;
    private String name;

    public UserBean() {
    }
    
    public UserBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if(name==null || name.trim().equals("")) {
            return "游客";
        }
        return name;
    }
    
    public void quit() {
        id = name = null;
    }
    
    public boolean notSignIn() {
        if(name==null || name.trim().equals("")){
            return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }
}
