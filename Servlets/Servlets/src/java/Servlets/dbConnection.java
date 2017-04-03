/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "dbConnection", urlPatterns = {"/dbConnection"})
public class dbConnection extends HttpServlet {

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
            out.println("<title>Servlet dbConnection</title>");            
            out.println("</head>");
            out.println("<body>");
           // out.println("<h1>Servlet dbConnection at " + request.getContextPath() + "</h1>");
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
     String JDBCDriver = "com.mysql.jdbc.Driver";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
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
       // processRequest(request, response);
         String user=request.getParameter("LoginForm[username]");
        String password=request.getParameter("LoginForm[password]");
        String host="localhost";
        String dbname="s_bgalla";
        Connection conn=null;
        conn= JavaJDBC.Connectdb(user, password, host, dbname);
        if (conn == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbConnection</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Invalid username and password.Relogin</h1>");
            out.println("</body>");
            out.println("</html>"); 
            rd.forward(request, response);
        }
        if(conn !=null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);    
            session.setAttribute("password", password);
            session.setAttribute("host", host);        
            session.setAttribute("driver", JDBCDriver);
            session.setAttribute("conn", conn);        
            ServletContext context = getServletConfig().getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/maintaindb.html");
            rd.forward(request, response);
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
