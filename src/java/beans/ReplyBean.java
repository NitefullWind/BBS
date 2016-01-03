/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author LY
 */
public class ReplyBean{

    private String topicId;
    private TopicBean topic;
    private List replys;
    private OpDB db;
    private boolean hasGetTpic;
    /**
     * Creates a new instance of ReplyBean
     */
    public ReplyBean() {
        topic = new TopicBean();
        replys = new ArrayList();
        hasGetTpic = false;
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        topicId = request.getParameter("id");
    }

    public TopicBean getTopic() {        
        if(topicId!=null && !topicId.trim().equals("") && !hasGetTpic) {
            //从数据库中查找该主题
            List list = getDb().execSelect("select * from tbl_topic where id=?", new ArrayList<String>(){{add(topicId);}});
            if(list!=null && !list.isEmpty()) {
                list = (List)list.get(0);
            }
            topic.setTitle((String)list.get(1));
            topic.setContent((String)list.get(2));
            topic.setAuthor((String)list.get(3));
            topic.setSubmitDate((String)list.get(4));
            topic.setSubmitTime((String)list.get(5));
            hasGetTpic = true;
        }
        return topic;
    }

    public void setTopic(TopicBean topic) {
        this.topic = topic;
    }

    public List getReplys() {
        if(topicId!=null && !topicId.trim().equals("") && replys!=null && replys.isEmpty() && hasGetTpic) {
            //从数据库中查找该主题的所有回复
            List list = db.execSelect("select tbl_reply.id, tbl_users.name, replyUser, content, submitDate, submitTime\n" +
                                        "from tbl_reply, tbl_users\n" +
                                        "where topicId=? and author=tbl_users.name", 
                                    new ArrayList<String>(){{add(topicId);}});
            for (int i = 0; i < list.size(); i++) {
                List row = (List)list.get(i);
                Reply reply = new Reply();
                reply.setId((String)row.get(0));
                reply.setAuthor((String)row.get(1));
                reply.setReplyUser((String)row.get(2));
                reply.setContent((String)row.get(3));
                reply.setSubmitDate((String)row.get(4));
                reply.setSubmitTime((String)row.get(5));
                //如果回复作者是主题作者，则将回复的extraMess设为“楼主”
                if(reply.getAuthor().equals(topic.getAuthor())){
                    reply.setExtraMess("楼主");
                }
                replys.add(reply);
            }
        }
        return replys;
    }

    public void setReplys(List replys) {
        this.replys = replys;
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
}
