/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author NitefullWind
 */
@ManagedBean
@RequestScoped
public class TopicListBean {
    private String id;
    private String title;
    private String content;
    private String author;
    private String submitTime;
    private List data = null;
    public OpDB db;
    public UserBean user;
    
    /**
     * Creates a new instance of topicListBean
     */
    public TopicListBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
    
    public void setData(List data) {
        this.data = data;
    }
    public OpDB getDb() {
        return db;
    }

    public void setDb(OpDB db) {
        this.db = db;
    }
    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }
    
    public List getData() {
        if(data == null) {
            data = new ArrayList();
            List list = db.execSelect("select * from tbl_topic");
            for(int i=0; i<list.size(); i++) {
                List row = (List)list.get(i);
                TopicBean topic = new TopicBean();
                topic.setTitle((String)row.get(1));
                topic.setContent((String)row.get(2));
                topic.setAuthor((String)row.get(3));
                topic.setSubmitTime((String)row.get(4));
                data.add(topic);
            }
        }
        return data;
    }
    
    //增加新主题
    public int insertData() {
        OpDB db = new OpDB();
        List parameterList = new ArrayList();
        parameterList.add(id);
        parameterList.add(title);
        parameterList.add(content);
        parameterList.add(author);
        parameterList.add(submitTime);
        int c = db.execUpdate("insert into tbl_topic (id,title,content,author,submitTime) values (?,?,?,?,?)", parameterList);
        return c;
    }
    
    //根据主题号在回复表中取信息
    public List getRecordResponse(){
        List list=new ArrayList();
//        OpDB db = new OpDB();
//        list = db.execSelect("select * from tbl_topic");
//        for(int i=0; i<list.size(); i++) {
//            List row = (List)list.get(i);
//            TopicBean topic = new TopicBean();
//            for(int j=0; j<row.size(); i++) {
//                topic.title = (String)row.get(1);
//                topic.content = (String)row.get(2);
//                topic.author = (String)row.get(3);
//                topic.submitTime = (String)row.get(4);
//            }
//            data.add(topic);
//        }
        return list;
    }
    
    public String publish() {
        if(getUser().getId() == null) {
            return "notSignIn";
        }
        return "signIned";
    }
}
