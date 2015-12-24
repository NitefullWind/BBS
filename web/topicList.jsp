<%-- 
    Document   : topicList
    Created on : 2015-12-23, 14:53:32
    Author     : NitefullWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <h:outputText value="#{topicListBean.author}"/>
            <h:outputText value="#{topicListBean.title}"/>
        </f:view>
    </body>
</html>
