/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private String author;
    private OpDB db;
    private UserBean user;
    private HttpServletResponse response;

    /**
     * Creates a new instance of DelBean
     */
    public DelBean() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
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
        
        //检查用户权限是否能够删除文章
        if(!checkGrade()) {
            return "权限不足";
        }
        
        boolean isOk = false;
        
        //根据type执行删除操作
        if("topic".equals(type)) {
            isOk = db.execUpdate("DELETE FROM `bbs`.`tbl_topic` WHERE  `id`=?;", new ArrayList(){{add(topicId);}})==1;
        } else if("reply".equals(type)) {
            isOk = db.execUpdate("DELETE FROM `bbs`.`tbl_reply` WHERE  `id`=?;", new ArrayList(){{add(replyId);}})==1;
        }
        
        //返回结果
        if(isOk) {
            String url = "";
            if("topic".equals(type)) {
                url = "topicList.xhtml";
            }else{
                url = "topicShow.xhtml?tid="+getTopicId();
            }
            try {
                response.sendRedirect(url);
                return "success";
            } catch (IOException ex) {
                Logger.getLogger(DelBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "删除失败";
        } else {
            return "删除失败";
        }
    }
    
    private boolean checkGrade() {
        if(user==null ||type==null) {
            return false;
        }
        //获取用户名
        String userName = user.getName();
        if(userName==null || userName.trim().equals("")){
            return false;
        }
        //如果登陆用户是管理员，则有权限删除
        if(!db.execSelect("select name from tbl_manager where name='"+userName+"'").isEmpty()) {
            return true;
        }
        //获取作者名
        if("topic".equals(type)) {
            author = db.execSelect("select author from tbl_topic where id="+topicId, 0, 0);
        } else if("reply".equals(type)) {
            author = db.execSelect("select author from tbl_reply where id="+replyId, 0, 0);
        }
        if(userName.endsWith(author)) {
            return true;
        }
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
