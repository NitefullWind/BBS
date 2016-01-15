/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;
/**
 *
 * @author NitefullWind
 */
public class OpDB {

    private Context ic = null;
    private DataSource source = null;
    private Connection con = null;
    private PreparedStatement pstmt = null;
    
    //获取数据源，连接数据库
    public boolean connectDB(){
        try {
            ic = new InitialContext();
            source = (DataSource) ic.lookup("BBS");
            con = source.getConnection();
            return true;
        } catch (NamingException ex) {
            Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
//    //简单的查询，返回一个字符串
//    public String getUserId(String name, String pwd){
////         where name=? and password=?
//        String sql = "select * from tbl_users";
//        String result = null;
//                    ArrayList l = new ArrayList();
//        if(connectDB()){
//            try {
//                pstmt = con.prepareStatement (
//                        sql,
//                        ResultSet.TYPE_SCROLL_SENSITIVE,
//                        ResultSet.CONCUR_UPDATABLE
//                );
//                //设置替换参数
////                pstmt.setString(1, name);
////                pstmt.setString(2, pwd);
//                //执行查询
//                results = pstmt.executeQuery();
//                while(results.next()){
//                    l.add(results.getString(1));
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                closeDB();
//            }
//        }
//        return result;
//    }
    
    //执行带参数的查询
    public List execSelect(String sql, List parameterList) {
        ResultSet results = null;
        List list = new ArrayList();
        
        if(con == null) {
            if(!connectDB()){
                return list;
            }
        }
//        if(connectDB()){
            try { 
                pstmt = con.prepareStatement(sql);
                for(int i=1; i<parameterList.size()+1; i++) {
                    pstmt.setString(i, parameterList.get(i-1).toString());
                }
                results = pstmt.executeQuery();
                //获取查询到的列数
                int colCount = results.getMetaData().getColumnCount();
                while(results.next()){
                    //将每一列的值放入一个row
                    List row = new ArrayList();
                    for(int i=1; i<=colCount; i++) {
                        row.add(results.getString(i));
                    }
                    //将一个row插入list
                    list.add(row);
                }
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                closeDB();
            }
//        }
        return list;
    }
//    @Override
    public List execSelect(String sql) {
        List nullList = new ArrayList();
        return execSelect(sql, nullList);
    }
    
//    @Override 根据行号，列号获取一个字符串
    public String execSelect(String sql, int rowIndex, int colIndex) {
        List list = execSelect(sql);
        if(list!=null && list.size() >= rowIndex) {
            list = (List)list.get(rowIndex);
            if(list!=null && list.size() >= colIndex) {
                return (String)list.get(colIndex);
            }
        }
        return "";
    }
//    @Override 根据行号获取一行记录
    public List execSelect(String sql, int rowIndex) {
        List list = execSelect(sql);
        if(list!=null && list.size() >= rowIndex) {
            list = (List)list.get(rowIndex);
            return list;
        }
        return null;
    }
    
    //执行带参数的如insert,delete,update操作,返回操作影响的行数
    public int execUpdate(String sql, List parameterList) {
        int resultCount = -1;
        if(con == null) {
            connectDB();
        }
//        if(connectDB()){
            try { 
                pstmt = con.prepareStatement(sql);
                for(int i=1; i<parameterList.size()+1; i++) {
                    pstmt.setString(i, parameterList.get(i-1).toString());
                }
                resultCount = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                closeDB();
            }
//        }
        return resultCount;
    }
    
    //关闭查询
    public void closeDB() {
        try {
            if(pstmt!=null){
                pstmt.close();
            }
            if(con != null) {
                con.close();
            }
            if(ic != null) {
                ic.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(OpDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
