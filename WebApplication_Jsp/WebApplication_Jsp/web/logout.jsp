<%-- 
    Document   : logout
    Created on : Aug 11, 2016, 11:31:29 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

    String user = (String) session.getAttribute("user");


    session = request.getSession(false);

    
    if (session != null) {
        session.invalidate();
        request.getRequestDispatcher("/index.jsp?msg=Succesfully Logged Out").forward(request, response);
    }
%>

    </body>
</html>
