/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NitefullWind
 */
public class DelBean extends BaseBean{
    private String info;
    private String type;
    private String topicId;
    private String replyId;
    private String content;
    private OpDB db;
    private UserBean user;

    /**
     * Creates a new instance of DelBean
     */
    public DelBean() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        info = request.getParameter("info");
        if(info!=null && !info.trim().equals("")) {
            String s[] = info.split("_");
            if(s.length==4) {
                type = s[0];
                topicId = s[1];
                replyId = s[2];
                content = s[3];
            }else{
                type = topicId = replyId = content = null;
            }
        }
    }
    public String del() {
        boolean isOk = false;
        
        //根据type执行删除操作
        if("topic".equals(type)) {
            isOk = db.execUpdate("DELETE FROM `bbs`.`tbl_topic` WHERE  `id`=?;", new ArrayList(){{add(topicId);}})==1;
        } else if("reply".equals(type)) {
            isOk = db.execUpdate("DELETE FROM `bbs`.`tbl_reply` WHERE  `id`=?;", new ArrayList(){{add(replyId);}})==1;
        }
        
        //返回结果
        if(isOk) {
            return "success";
        } else {
            return "failure";
        }
    }
    
    private boolean checkGrade() {
        return false;
    }

    public String getType() {
        if("topic".equals(type)) {
            return "主题：";
        }else if("reply".equals(type)) {
            return "回复：";
        }
        return "";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getTopicId() {
        return topicId;
    }
    
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public OpDB getDb() {
        return db;
    }

    public void setDb(OpDB db) {
        this.db = db;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
