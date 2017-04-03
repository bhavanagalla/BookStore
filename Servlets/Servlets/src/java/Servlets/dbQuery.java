/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
@WebServlet(name = "dbQuery", urlPatterns = {"/dbQuery"})
public class dbQuery extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbQuery</title>");            
            out.println("</head>");
            out.println("<body>");
           // out.println("<h1>Servlet dbQuery at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
         HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        Connection conn=(Connection) session.getAttribute("conn");   
        response.setContentType("text/html;charset=UTF-8");
        
        String stitle=request.getParameter("search");
        String tableName1="titles";
        String year=request.getParameter("year");       
        String searchby=request.getParameter("searchby");
        String tname=request.getParameter("Selecttable");
        String[] fields=request.getParameterValues("fields");
        String all = request.getParameter("all");
        
        ResultSet rs1=null;
        ResultSet rs2;
        ResultSet rs3=null;
        //System.out.println("tnameeee"+tname);
        //out.println(searchby);
        if(searchby.equals("FirstName"))
            {
                String tb = request.getParameter("tb");
                //out.println(tb);
                if(!"".equals(tb))
                {
                    try {
                       rs2 = JavaJDBC.Searchfn(tb, conn);
                       //out.println(searchby);
                       if (rs2.next()) {
                           try {
                               if (tb.equals(rs2.getString("FirstName").trim()))
                                {        
                                        rs1=JavaJDBC.Searchfnn(tb,conn);    
                                        response.setContentType("text/html");
                                        //out.println(tb);
                                        out.println("<html><head>");
                                        out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                        out.println("<div id=\"main\"><h1>Book Information</h1><table width=100%>");
                                        out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                        while (rs1.next()) {
                                            int a = rs1.getInt("AuthorID");
                                            String fn = rs1.getString("FirstName");
                                            String ln = rs1.getString("LastName");
                                            String isbn = rs1.getString("ISBN");
                                            String t = rs1.getString("Title"); 
                                            String e = rs1.getString("EditionNumber");
                                            String c = rs1.getString("Copyright");
                                            out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>"); 
                                        }
                                        out.println("</table>");
                                        out.println("</html></body>");
                                   } 
                                   else
                            {
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Servlet dbQuery</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1 align=center>No Records Found</h1>");
                                out.println("</body>");
                                out.println("</html>");
                            }
                        } catch (SQLException ex) {
                               Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                        else
                            {
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Servlet dbQuery</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1 align=center>No Records Found</h1>");
                                out.println("</body>");
                                out.println("</html>");
                            }
                    }catch (Exception ex) {
                                       Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
           else if(searchby.equals("LastName"))
        {
            String tb = request.getParameter("tb");
            //out.println(tb);
            if(!"".equals(tb))
            {
                try {
                     rs2 = JavaJDBC.Searchln(tb, conn);
                  // out.println(searchby);
                   if (rs2.next()) {
                       try {
                            if (tb.equals(rs2.getString("LastName").trim()))
                            {
                                    rs1=JavaJDBC.Searchlnn(tb,conn);     
                                    response.setContentType("text/html");
                                   // out.println(tb);
                                    out.println("<html><head>");
                                    out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                    out.println("<div id=\"main\"><h1>Book Information</h1><table width=100%>");
                                    out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                    while (rs1.next()) {
                                        int a = rs1.getInt("AuthorID");
                                        String fn = rs1.getString("FirstName");
                                        String ln = rs1.getString("LastName");
                                        String isbn = rs1.getString("ISBN");
                                        String t = rs1.getString("Title"); 
                                        String e = rs1.getString("EditionNumber");
                                        String c = rs1.getString("Copyright");
                                        out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>"); 
                                    }
                                    out.println("</table>");
                                    out.println("</html></body>");
                               } 
                       
                    } catch (SQLException ex) {
                           Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }catch (Exception ex) {
                                   Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
         else if(searchby.equals("ISBN"))
        {
            String tb = request.getParameter("tb");
            //out.println(tb);
            if(!"".equals(tb))
            {
                try {
                    rs2 = JavaJDBC.Searchisbn(tb, conn);
                  // out.println(searchby);
                   if (rs2.next()) {
                       try {
                            if (tb.equals(rs2.getString("ISBN").trim()))
                            {
                                    rs1=JavaJDBC.Searchisbnn(tb,conn);   
                                    response.setContentType("text/html");
                                    out.println(tb);
                                    out.println("<html><head>");
                                    out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                    out.println("<div id=\"main\"><h1>Book Information</h1><table width=100%>");
                                    out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                    while (rs1.next()) {
                                        int a = rs1.getInt("AuthorID");
                                        String fn = rs1.getString("FirstName");
                                        String ln = rs1.getString("LastName");
                                        String isbn = rs1.getString("ISBN");
                                        String t = rs1.getString("Title"); 
                                        String e = rs1.getString("EditionNumber");
                                        String c = rs1.getString("Copyright");
                                        out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>"); 
                                    }
                                    out.println("</table>");
                                    out.println("</html></body>");
                               } 
                       
                    } catch (SQLException ex) {
                           Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }catch (Exception ex) {
                                   Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
          else if(!"".equals(year) && !"".equals(stitle))
        {
            
                try {
                    rs2 = JavaJDBC.Searchyt(year,stitle, conn);
                    //out.println(year);
                   if (rs2.next()) {
                       try {
                            if (stitle.equals(rs2.getString("Title").trim()) && year.equals(rs2.getString("Copyright").trim()))
                            {
                                    rs1 = JavaJDBC.Searchytn(year,stitle,conn); 
                                    //out.println(stitle);
                                    response.setContentType("text/html");
                                    out.println("<html><head>");
                                    out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                    out.println("<div id=\"main\"><h1>Search Result</h1><table width=100%>");
                                    out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                    while (rs1.next()) {
                                        int a = rs1.getInt("AuthorID");
                                        String fn = rs1.getString("FirstName");
                                        String ln = rs1.getString("LastName");
                                        String isbn = rs1.getString("ISBN");
                                        String t = rs1.getString("Title"); 
                                        String e = rs1.getString("EditionNumber");
                                        String c = rs1.getString("Copyright");
                                        out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>"); 
                                    }
                                    out.println("</table>");
                                    out.println("</html></body>");
                               } 
                       
                    } catch (SQLException ex) {
                           Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }catch (Exception ex) {
                                   Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
        } 
          if(tname.equals("Authors"))
        {
           if(fields.length==3 &&fields[0].equals("AuthorID")&&fields[1].equals("FirstName")&&fields[2].equals("LastName"))
        {
            rs1=JavaJDBC.Searchfn14(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Authors Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><tr>");
                     while (rs1.next()) {
                         String fn = rs1.getString("FirstName");
                         String ln = rs1.getString("LastName");
                         int a = rs1.getInt("AuthorID");
                         out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            else if(fields[0].equals("AuthorID")&&fields[1].equals("FirstName"))
        {
            rs1=JavaJDBC.Searchfn1(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Authors Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Author ID</th><th>First Name</th><tr>");
                     while (rs1.next()) {
                         String fn = rs1.getString("FirstName");
                         int a = rs1.getInt("AuthorID");
                         out.println("<tr><td>" + a + "</td><td>" + fn + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            else if(fields[0].equals("AuthorID")&&fields[1].equals("LastName"))
        {
            rs1=JavaJDBC.Searchfn2(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Authors Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Author ID</th><th>Last Name</th><tr>");
                     while (rs1.next()) {
                         String ln = rs1.getString("LastName");
                         int a = rs1.getInt("AuthorID");
                         out.println("<tr><td>" + a + "</td><td>" + ln + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
           else if(fields[0].equals("FirstName")&&fields[1].equals("LastName"))
        {
            rs1=JavaJDBC.Searchfn3(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Authors Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>First Name</th><th>Last Name</th><tr>");
                     while (rs1.next()) {
                         String ln = rs1.getString("LastName");
                         String fn = rs1.getString("FirstName");
                         out.println("<tr><td>" + fn + "</td><td>" + ln + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
         else if(tname.equals("Titles")){
            if(fields.length==4 &&fields[0].equals("ISBN")&&fields[1].equals("Title")&&fields[2].equals("EditionNumber")&&fields[3].equals("Copyright"))
        {
           rs1=JavaJDBC.Searchfn15(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String t = rs1.getString("Title");
                         String e = rs1.getString("EditionNumber");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
             if(fields.length==3 &&fields[0].equals("ISBN")&&fields[1].equals("Title")&&fields[2].equals("EditionNumber"))
        {
            rs1=JavaJDBC.Searchfn4(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Title</th><th>Edition Number</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String t = rs1.getString("Title");
                         String e = rs1.getString("EditionNumber");
                         out.println("<tr><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
            else if(fields.length==3 &&fields[0].equals("ISBN")&&fields[1].equals("Title")&&fields[2].equals("Copyright"))
        {
            rs1=JavaJDBC.Searchfn5(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Title</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String t = rs1.getString("Title");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + isbn + "</td><td>" + t + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
            else if(fields.length==3 &&fields[0].equals("ISBN")&&fields[1].equals("EditionNumber")&&fields[2].equals("Copyright"))
        {
            rs1=JavaJDBC.Searchfn7(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Edition Number</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String e = rs1.getString("EditionNumber");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + isbn + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
            else if(fields.length==3 &&fields[0].equals("Title")&&fields[1].equals("EditionNumber")&&fields[2].equals("Copyright"))
        {
            rs1=JavaJDBC.Searchfn6(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String t = rs1.getString("Title");
                         String e = rs1.getString("EditionNumber");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
            else if(fields.length==2 &&fields[0].equals("ISBN")&&fields[1].equals("Title"))
        {
            rs1=JavaJDBC.Searchfn8(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Title</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String t = rs1.getString("Title");
                         out.println("<tr><td>" + isbn + "</td><td>" + t + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        } else if(fields.length==3 &&fields[0].equals("EditionNumber")&&fields[1].equals("Copyright"))
        {
            rs1=JavaJDBC.Searchfn13(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Edition Number</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String e = rs1.getString("EditionNumber");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + e + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
        else if(fields.length==2 &&fields[0].equals("ISBN")&&fields[1].equals("Copyright"))
        {
            rs1=JavaJDBC.Searchfn10(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String c = rs1.getString("Copyright");
                         out.println("<tr><td>" + isbn + "</td><td>" + c + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
        else if(fields.length==2 &&fields[0].equals("Title")&&fields[1].equals("EditionNumber"))
        {
           rs1=JavaJDBC.Searchfn11(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Title</th><th>Edition Number</th><tr>");
                     while (rs1.next()) {
                         String t = rs1.getString("Title");
                         String e = rs1.getString("EditionNumber");
                         out.println("<tr><td>" + t + "</td><td>" + e + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
             else if(fields.length==2 &&fields[0].equals("ISBN")&&fields[1].equals("EditionNumber"))
        {
           rs1=JavaJDBC.Searchfn9(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>ISBN</th><th>Edition Number</th><tr>");
                     while (rs1.next()) {
                         String isbn = rs1.getString("ISBN");
                         String e = rs1.getString("EditionNumber");
                         out.println("<tr><td>" + isbn + "</td><td>" + e + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
             else if(fields.length==2 &&fields[0].equals("Title")&&fields[1].equals("Copyright"))
        {
           rs1=JavaJDBC.Searchfn12(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Titles Table</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Title</th><th>Copyright</th><tr>");
                     while (rs1.next()) {
                         String t = rs1.getString("Title");
                         String cr = rs1.getString("Copyright");
                         out.println("<tr><td>" + t + "</td><td>" + cr + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
        }  
          else if(tname.equals("AuthorISBN")){
            rs1=JavaJDBC.Searchfn16(conn);
            if(rs1!=null){
                try {
                     response.setContentType("text/html");
                     out.println("<html><head>");
                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                     out.println("<div id=\"main\"><h1>Authors ISBN</h1><table width=100%>");
                     out.println("<table width=100%>");
                     out.println("<tr><th>Author ID</th><th>ISBN</th></tr>");
                     while (rs1.next()) {
                         int a = rs1.getInt("AuthorID");
                         String isbn = rs1.getString("ISBN");
                         out.println("<tr><td>" + a + "</td><td>" + isbn + "</td></tr>");
                     }
                     out.println("</table>");
                     out.println("</html></body>");
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
//        if(all.equals(""))
//        {
//                    try {
//                         ResultSet rs ;
//                         rs=JavaJDBC.Searchfn17(conn);
//                         response.setContentType("text/html");
//                         out.println("<html><head>");
//                         out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
//                         out.println("<div id=\"main\"><h1>Books Information</h1><table width=100%>");
//                         out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
//                         while (rs.next()) {
//                             int a = rs.getInt("AuthorID");
//                             String fn = rs.getString("FirstName");
//                             String ln = rs.getString("LastName");
//                             String isbn = rs.getString("ISBN");
//                             String t = rs.getString("Title");
//                             String e = rs.getString("EditionNumber");
//                             String c = rs.getString("Copyright");
//                             out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
//                         }
//                         out.println("</table>");
//                         out.println("</html></body>");
//                    } catch (SQLException ex) {
//                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//        }
//        else
//        {
//            out.println("To view Book information select checkbox");
//        }
        if(!"".equals(stitle))
        {
            out.println(stitle);
                    try {
                        rs2=JavaJDBC.Searchbt(tableName1, stitle, conn);
                        if (rs2.next()) {
                            try {
                                out.println(stitle);
                         if (stitle.equals(rs2.getString("Title").trim()))
                         {
                                 rs1=JavaJDBC.Searchbtn(stitle, conn)  ;
                                 response.setContentType("text/html");
                                 out.println(stitle);
                                 out.println("<html><head>");
                                 out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                 out.println("<div id=\"main\"><h1>Book Information</h1><table width=100%>");
                                 out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                 while (rs1.next()) {
                                     int a = rs1.getInt("AuthorID");
                                     String fn = rs1.getString("FirstName");
                                     String ln = rs1.getString("LastName");
                                     String isbn = rs1.getString("ISBN");
                                     String t = rs1.getString("Title"); 
                                     String e = rs1.getString("EditionNumber");
                                     String c = rs1.getString("Copyright");
                                     out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>"); 
                                 }
                                 out.println("</table>");
                                 out.println("</html></body>");
                                 
                          }  
                            } catch (SQLException ex) {
                                Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     }else
                            {
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Servlet dbQuery</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1 align=center>No Records Found</h1>");
                                out.println("</body>");
                                out.println("</html>");
                            }
                    } catch (SQLException ex) {
                        Logger.getLogger(dbQuery.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
