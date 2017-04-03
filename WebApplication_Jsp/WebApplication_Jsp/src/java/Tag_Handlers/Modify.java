/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tag_Handlers;

import java.JavaJDBC;
import java.sql.Connection;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author dell
 */
public class Modify extends SimpleTagSupport {
    private String fname;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    private String lname;
    private String isbn;
    private String title;
    private String edno;
    private String cr;
    private String u;
    private Connection conn;
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            
        String rs=null;
         
        //update
        if(getU()!=null)
        {
            JavaJDBC jj=new JavaJDBC();
            rs=jj.Update(getFname(), getLname(), getIsbn(), getTitle(), getEdno(), getCr(), getConn());
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbUpdateServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 align=center><font color=\"white\">Updated Successfully!!!</font></h2>");
            out.println("</body>");
        }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Insert tag", ex);
        }
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the edno
     */
    public String getEdno() {
        return edno;
    }

    /**
     * @param edno the edno to set
     */
    public void setEdno(String edno) {
        this.edno = edno;
    }

    /**
     * @return the cr
     */
    public String getCr() {
        return cr;
    }

    /**
     * @param cr the cr to set
     */
    public void setCr(String cr) {
        this.cr = cr;
    }

    /**
     * @return the u
     */
    public String getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(String u) {
        this.u = u;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
