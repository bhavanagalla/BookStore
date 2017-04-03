<%-- 
    Document   : dbUpdate
    Created on : Aug 8, 2016, 6:43:08 PM
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
        String msg = request.getParameter("msg");
            msg = (msg == null ? "" : msg);
            Connection conn = null;
            String fname,lname,isbn,title,edno,cr,i,u,d;
            
            %>            
            <%
        if(request.getParameter("insert")!=null){             
             conn = (Connection) session.getAttribute("conn");
            if (conn != null) {
                        
                    } else {
                        out.println("<h5>Connection failed</h5>");
                    }
                }
            fname=request.getParameter("ifname");
            lname=request.getParameter("ilname");
            isbn=request.getParameter("iisbn");
            title=request.getParameter("ititle");
            edno=request.getParameter("ieditionno");
            cr=request.getParameter("icopyright");
            i=request.getParameter("insert");
            
                pageContext.setAttribute("fn", fname);
                pageContext.setAttribute("ln", lname);
                pageContext.setAttribute("isbn", isbn);
                pageContext.setAttribute("t", title);
                pageContext.setAttribute("en", edno);
                pageContext.setAttribute("cr", cr);
                pageContext.setAttribute("i", i);
            %>
<tag:Insert conn='<%=conn%>' cr='<%=cr%>'  edno='<%=edno%>' fname='<%=fname%>' isbn='<%=isbn%>' lname='<%=lname%>' title='<%=title%>' i='<%=i%>'></tag:Insert>

<%
       if(request.getParameter("update")!=null){             
             conn = (Connection) session.getAttribute("conn");

                    if (conn != null) {
                        
                    } else {
                        out.println("<h5>Connection failed</h5>");
                    }
                }
            fname=request.getParameter("ufname");
            lname=request.getParameter("ulname");
            isbn=request.getParameter("uisbn");
            title=request.getParameter("utitle");
            edno=request.getParameter("ueditionno");
            cr=request.getParameter("ucopyright");
            u=request.getParameter("update");
            
                pageContext.setAttribute("fn", fname);
                pageContext.setAttribute("ln", lname);
                pageContext.setAttribute("isbn", isbn);
                pageContext.setAttribute("t", title);
                pageContext.setAttribute("en", edno);
                pageContext.setAttribute("cr", cr);
                pageContext.setAttribute("u", u);
                %>
                <tag:Modify conn='<%=conn%>' cr='<%=cr%>'  edno='<%=edno%>' fname='<%=fname%>' isbn='<%=isbn%>' lname='<%=lname%>' title='<%=title%>' u='<%=u%>'></tag:Modify>
                <%
         if(request.getParameter("delete")!=null){             
             conn = (Connection) session.getAttribute("conn");

                    if (conn != null) {
                        
                    } else {
                        out.println("<h5>Connection failed</h5>");
                    }
                }
            //
            fname=request.getParameter("dfname");
            lname=request.getParameter("dlname");
            isbn=request.getParameter("disbn");
            title=request.getParameter("dtitle");
            edno=request.getParameter("deditionno");
            cr=request.getParameter("dcopyright");
            d=request.getParameter("delete");
            //
                pageContext.setAttribute("fn", fname);
                pageContext.setAttribute("ln", lname);
                pageContext.setAttribute("isbn", isbn);
                pageContext.setAttribute("t", title);
                pageContext.setAttribute("en", edno);
                pageContext.setAttribute("cr", cr);
                pageContext.setAttribute("d", d);
                pageContext.setAttribute("conn", conn);

        %>
         <tag:Delete conn='<%=conn%>' cr='<%=cr%>'  edno='<%=edno%>' fname='<%=fname%>' isbn='<%=isbn%>' lname='<%=lname%>' title='<%=title%>' d='<%=d%>'></tag:Delete>
    </body>
</html>
