/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NitefullWind
 */
@ManagedBean
@SessionScoped
public class TopicBean {
    private String id;
    private String title;
    private String content;
    private String subContent;
    private String author;
    private String submitDate;
    private String submitTime;
    /**
     * Creates a new instance of Topic
     */
    public TopicBean() {
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

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubContent() {
        if(!getContent().isEmpty() && getContent().length()>20) {
            subContent = content.substring(0, 20);   //胜率内容为主要内容的前20个字符
            subContent += "...";                     //添加省略符号
        }else{
            subContent = content;
        }
        return subContent;
    }

    public void setSubContent(String submitContent) {
        this.subContent = submitContent;
    }
    
}
