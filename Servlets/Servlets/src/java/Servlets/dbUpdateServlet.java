/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet(name = "dbUpdateServlet", urlPatterns = {"/dbUpdateServlet"})
public class dbUpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet dbUpdateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dbUpdateServlet at " + request.getContextPath() + "</h1>");
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
        Connection conn=(Connection) session.getAttribute("conn");            
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String rs=null;
         //insert     
        if(request.getParameter("ifname") !=null && request.getParameter("ilname") !=null && request.getParameter("iisbn") !=null && request.getParameter("ititle") !=null && request.getParameter("ieditionno") !=null && request.getParameter("icopyright") !=null)
        {
            String fname=request.getParameter("ifname");
            String lname=request.getParameter("ilname");
            String isbn=request.getParameter("iisbn");
            String title=request.getParameter("ititle");
            String edno=request.getParameter("ieditionno");
            String cr=request.getParameter("icopyright");
            JavaJDBC jj=new JavaJDBC();
            rs=jj.Insert(fname, lname,isbn,title,edno,cr, conn);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbUpdateServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 align=center>Inserted Successfully!!!</h2>");
            out.println("</body>");
            out.println("</html>");
        }
        //update
        else if(request.getParameter("ufname") !=null && request.getParameter("ulname") !=null && request.getParameter("uisbn") !=null && request.getParameter("utitle") !=null && request.getParameter("ueditionno") !=null && request.getParameter("ucopyright") !=null)
        {
            //Author values
            String fname=request.getParameter("ufname");
            String lname=request.getParameter("ulname");
            String isbn=request.getParameter("uisbn");
            String title=request.getParameter("utitle");
            String edno=request.getParameter("ueditionno");
            String cr=request.getParameter("ucopyright");
            JavaJDBC jj=new JavaJDBC();
            rs=jj.Update(fname, lname,isbn,title,edno,cr, conn);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbUpdateServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 align=center>Updated Successfully!!!</h2>");
            out.println("</body>");
        }
        
        //delete
         else if(request.getParameter("dfname") !=null && request.getParameter("dlname") !=null && request.getParameter("disbn") !=null && request.getParameter("dtitle") !=null && request.getParameter("deditionno") !=null && request.getParameter("dcopyright") !=null)
        {
            String fname=request.getParameter("dfname");
            String lname=request.getParameter("dlname");
            String isbn=request.getParameter("disbn");
            String title=request.getParameter("dtitle");
            String edno=request.getParameter("deditionno");
            String cr=request.getParameter("dcopyright");
            JavaJDBC jj=new JavaJDBC();
            rs=jj.Delete(fname, lname,isbn,title,edno,cr, conn);
            out.println("<html>");
            out.println("<head>");
            //out.println("<title>Servlet dbUpdateServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 align=center>Deleted Successfully!!!</h2>");
            out.println("</body>");
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
