<%-- 
    Document   : dbQuery
    Created on : Aug 9, 2016, 1:43:20 AM
    Author     : dell
--%>

<%@page import="java.sql.Connection"%>
<%@taglib prefix="tag" uri="/WEB-INF/tlds/Jsptag_library.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
        Connection conn=null;
        String stitle=request.getParameter("search");
           String year=request.getParameter("year");
           String searchby=request.getParameter("searchby");
           String selecttable=request.getParameter("Selecttable");
           String[] fields=request.getParameterValues("fields");
           String all = request.getParameter("all");
           String tb=request.getParameter("tb");
        if(request.getParameter("Search")!=null){ 
                    conn = (Connection) session.getAttribute("conn");
                    
                    if (conn != null) {
                        
                    } else {
                         request.getRequestDispatcher("/index.jsp?msg=Database Connection Failed").forward(request, response);
                    }
           
                }
                pageContext.setAttribute("stitle", stitle);
                pageContext.setAttribute("year", year);
                pageContext.setAttribute("searchby", searchby);
                pageContext.setAttribute("Selecttable", selecttable);
                pageContext.setAttribute("fields", fields);
                pageContext.setAttribute("all", all);
                pageContext.setAttribute("tb", tb);
    
            %>
            <tag:Search conn='<%=conn%>' stitle='<%=request.getParameter("search")%>' all='<%=all%>' tb='<%=tb%>' fields='<%=fields%>' searchby='<%=searchby%>' year='<%=year%>' selecttable='<%=selecttable%>'></tag:Search>
    </body>
</html>
