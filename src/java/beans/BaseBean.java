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
public class BaseBean{
    private OpDB db;              //声明OpDB对象引用
    private String message;       //给用户的提示信息

    public void setDbu(OpDB dbu){
            this.db = dbu;
    }

    public void setMessage(String message){
            this.message = message;
    }	
    public String getMessage(){
            return this.message;
    }
}