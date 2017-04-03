/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tag_Handlers;

import java.JavaJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author dell
 */
public class Search extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
   private String[] fields;
   private String stitle;
    private String year;
    private String searchby;
    private String Selecttable;
    private String tb;
    private String all;
    private Connection conn;
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {      
            String tableName1="titles";          
            ResultSet rs1=null;
            ResultSet rs2;
            ResultSet rs3=null;

            if(getSearchby().equals("FirstName"))
                {
                    if(!"".equals(tb))
                    {
                        try {
                           rs2 = JavaJDBC.Searchfn(getTb(), getConn());
                           out.println(getSearchby());
                           if (rs2.next()) {
                               try {
                                   if (getTb().equals(rs2.getString("FirstName").trim()))
                                    {        
                                            rs1=JavaJDBC.Searchfnn(getTb(), getConn());
                                            out.println(getTb());
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
                                   Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                               }
                            }
                        }catch (Exception ex) {
                                           Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    }
                }
               else if(getSearchby().equals("LastName"))
            {
                if(!"".equals(tb))
                {
                    try {
                         rs2 = JavaJDBC.Searchln(getTb(), getConn());
                      //out.println(searchby);
                       if (rs2.next()) {
                           try {
                                if (getTb().equals(rs2.getString("LastName").trim()))
                                {
                                        rs1=JavaJDBC.Searchlnn(getTb(), getConn());    
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
                               Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                    }catch (Exception ex) {
                                       Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
             else if(getSearchby().equals("ISBN"))
            {
                if(!"".equals(tb))
                {
                    try {
                        rs2 = JavaJDBC.Searchisbn(getTb(), getConn());
                      // out.println(searchby);
                       if (rs2.next()) {
                           try {
                                if (getTb().equals(rs2.getString("ISBN").trim()))
                                {
                                        rs1=JavaJDBC.Searchisbnn(getTb(), getConn()); 
                                        out.println(getTb());
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
                               Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                    }catch (Exception ex) {
                                       Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
              else if(!"".equals(year) && !"".equals(stitle))
            {
                
                    try {
                        rs2 = JavaJDBC.Searchyt(getYear(), getStitle(), getConn());
                        //out.println(year);
                       if (rs2.next()) {
                           try {
                                if (getStitle().equals(rs2.getString("Title").trim()) && getYear().equals(rs2.getString("Copyright").trim()))
                                {
                                        rs1 = JavaJDBC.Searchytn(getYear(), getStitle(), getConn()); 
                                        //out.println(stitle);
                                        //response.setContentType("text/html");
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
                               Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }
                    }catch (Exception ex) {
                                       Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                     }

            }
            if(getSelecttable().equals("Authors"))
          {
             if(getFields().length==3 &&getFields()[0].equals("AuthorID")&&getFields()[1].equals("FirstName")&&getFields()[2].equals("LastName"))
          {
              rs1=JavaJDBC.Searchfn14(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
              else if(getFields()[0].equals("AuthorID")&&getFields()[1].equals("FirstName"))
          {
              rs1=JavaJDBC.Searchfn1(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
              else if(getFields()[0].equals("AuthorID")&&getFields()[1].equals("LastName"))
          {
              rs1=JavaJDBC.Searchfn2(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
             else if(getFields()[0].equals("FirstName")&&getFields()[1].equals("LastName"))
          {
              rs1=JavaJDBC.Searchfn3(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
          }
           else if(getSelecttable().equals("Titles")){
              if(getFields().length==4 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("Title")&&getFields()[2].equals("EditionNumber")&&getFields()[3].equals("Copyright"))
          {
             rs1=JavaJDBC.Searchfn15(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
               if(getFields().length==3 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("Title")&&getFields()[2].equals("EditionNumber"))
          {
              rs1=JavaJDBC.Searchfn4(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
              else if(getFields().length==3 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("Title")&&getFields()[2].equals("Copyright"))
          {
              rs1=JavaJDBC.Searchfn5(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
              else if(getFields().length==3 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("EditionNumber")&&getFields()[2].equals("Copyright"))
          {
              rs1=JavaJDBC.Searchfn7(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
              else if(getFields().length==3 &&getFields()[0].equals("Title")&&getFields()[1].equals("EditionNumber")&&getFields()[2].equals("Copyright"))
          {
              rs1=JavaJDBC.Searchfn6(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
              else if(getFields().length==2 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("Title"))
          {
              rs1=JavaJDBC.Searchfn8(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          } else if(getFields().length==3 &&getFields()[0].equals("EditionNumber")&&getFields()[1].equals("Copyright"))
          {
              rs1=JavaJDBC.Searchfn13(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
          else if(getFields().length==2 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("Copyright"))
          {
              rs1=JavaJDBC.Searchfn10(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
          else if(getFields().length==2 &&getFields()[0].equals("Title")&&getFields()[1].equals("EditionNumber"))
          {
             rs1=JavaJDBC.Searchfn11(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
               else if(getFields().length==2 &&getFields()[0].equals("ISBN")&&getFields()[1].equals("EditionNumber"))
          {
             rs1=JavaJDBC.Searchfn9(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
               else if(getFields().length==2 &&getFields()[0].equals("Title")&&getFields()[1].equals("Copyright"))
          {
             rs1=JavaJDBC.Searchfn12(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }
          }  
            else if(getSelecttable().equals("AuthorISBN")){
              rs1=JavaJDBC.Searchfn16(getConn());
              if(rs1!=null){
                  try {
                       //response.setContentType("text/html");
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
                          Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
          }         if(getAll().equals(""))
                    {
                                try {
                                     ResultSet rs ;
                                     rs=JavaJDBC.Searchfn17(getConn());
                                     //response.setContentType("text/html");
                                     out.println("<html><head>");
                                     out.println("<link rel=\"stylesheet\" href=\"css/2.css\" type=\"text/css\" /></head><body>");
                                     out.println("<div id=\"main\"><h1>Books Information</h1><table width=100%>");
                                     out.println("<tr><th>Author ID</th><th>First Name</th><th>LastName</th><th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><tr>");
                                     while (rs.next()) {
                                         int a = rs.getInt("AuthorID");
                                         String fn = rs.getString("FirstName");
                                         String ln = rs.getString("LastName");
                                         String isbn = rs.getString("ISBN");
                                         String t = rs.getString("Title");
                                         String e = rs.getString("EditionNumber");
                                         String c = rs.getString("Copyright");
                                         out.println("<tr><td>" + a + "</td><td>" + fn + "</td><td>" + ln + "</td><td>" + isbn + "</td><td>" + t + "</td><td>" + e + "</td><td>" + c + "</td></tr>");
                                     }
                                     out.println("</table>");
                                     out.println("</html></body>");
                                } catch (SQLException ex) {
                                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                    }
                    else
                    {
                        out.println("To view Book information select checkbox");
                    }
    
            if(!"".equals(stitle))
            {
                        try {
                            rs2=JavaJDBC.Searchbt(tableName1, getStitle(), getConn());
                            if (rs2.next()) {
                                try {
                                    
                             if (getStitle().equals(rs2.getString("Title").trim()))
                             {
                                     rs1=JavaJDBC.Searchbtn(getStitle(), getConn())  ;
                                     //response.setContentType("text/html");
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
                                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         }else
                                {
                                    out.println("<html>");
                                    out.println("<head>");
                                    out.println("<title>Servlet Search</title>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("<h1 align=center>No Records Found</h1>");
                                    out.println("</body>");
                                    out.println("</html>");
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
  

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Search tag", ex);
        }
    
    }

    /**
     * @return the fields
     */
    public String[] getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(String[] fields) {
        this.fields = fields;
    }

    /**
     * @return the stitle
     */
    public String getStitle() {
        return stitle;
    }

    /**
     * @param stitle the stitle to set
     */
    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the searchby
     */
    public String getSearchby() {
        return searchby;
    }

    /**
     * @param searchby the searchby to set
     */
    public void setSearchby(String searchby) {
        this.searchby = searchby;
    }

    /**
     * @return the Selecttable
     */
    public String getSelecttable() {
        return Selecttable;
    }

    /**
     * @param Selecttable the Selecttable to set
     */
    public void setSelecttable(String Selecttable) {
        this.Selecttable = Selecttable;
    }

    /**
     * @return the tb
     */
    public String getTb() {
        return tb;
    }

    /**
     * @param tb the tb to set
     */
    public void setTb(String tb) {
        this.tb = tb;
    }

    /**
     * @return the all
     */
    public String getAll() {
        return all;
    }

    /**
     * @param all the all to set
     */
    public void setAll(String all) {
        this.all = all;
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
