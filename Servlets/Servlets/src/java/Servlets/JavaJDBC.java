/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class JavaJDBC {
     Connection conn = null;
     public static Connection Connectdb(String username, String password, String host, String dbname){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://" + host +"/"+ dbname+ "";
            Connection conn =DriverManager.getConnection(url,username,password);
            
            return conn;
        }
        catch(SQLException  e)
        {
            //JOptionPane.showMessageDialog(null, e);
            return null;
        }
        catch(ClassNotFoundException ex)
            {
               //JOptionPane.showMessageDialog(null, ex);
                return null;
        }
    }
      public static  int getAuthId(Connection conn){


            int maxauthid = 0;
            try{
                    String query1 = "Select * from s_bgalla.Authors";

                    PreparedStatement pst2 = conn.prepareStatement(query1);
                    ResultSet rs2 = pst2.executeQuery();

                    while(rs2.next())
                    {
                            maxauthid = rs2.getInt(1);

                    }
                    rs2.close();
                    pst2.close();

            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
            }
            System.out.println("GetAuthId :"+maxauthid);

            return maxauthid+1;
			
	}
     //Search author id by isbn
	public static  int getAuthId1(String ISBN,Connection conn){
		int maxauthid = 0;
		try {
			String query = "Select AuthorID from s_bgalla.AuthorISBN where ISBN = ?";
				
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,ISBN);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				maxauthid = rs.getInt(1);
			}
			rs.close();
			ps.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
                return maxauthid;
			
	}
	//Insert
    public String Insert(String fname, String lname,String isbn, String title, String edition, String cr, Connection con)
    {
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(isbn);
        System.out.println(title);
        System.out.println(edition);
        System.out.println(cr);
        try{
            PreparedStatement pst1;
            PreparedStatement pst2;
            PreparedStatement pst3;
            int Auth_id = JavaJDBC.getAuthId(con);
            String sql1 = "Insert into Authors(FirstName,LastName) values(?,?)";
            pst1 = con.prepareStatement(sql1);
            pst1.setString(1, fname );
            pst1.setString(2, lname);
            pst1.execute();
            String sql3 = "Insert into titles(ISBN, Title, EditionNumber, Copyright) values(?,?,?,?)";
            pst3 = con.prepareStatement(sql3);
            pst3.setString(1, isbn );
            pst3.setString(2, title);
            pst3.setString(3, edition );
            pst3.setString(4, cr);
            pst3.execute();
            String sql2 = "Insert into Authorisbn(AuthorID,ISBN) values(?,?)";
            pst2 = con.prepareStatement(sql2);
            pst2.setInt(1, Auth_id );
            pst2.setString(2, isbn);
            pst2.execute();
            pst1.close();
            pst3.close();
            pst2.close();
             System.out.println("Inserted successfully!!");
            return "Inserted successfully!!";
            }
            catch(Exception e){
                //JOptionPane.showMessageDialog(null, e);
                return "failed";
            }
    }
    //Update
public String Update(String fname, String lname,String isbn, String title, String edition, String cr, Connection con)
    {
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(isbn);
        System.out.println(title);
        System.out.println(edition);
        System.out.println(cr);
        int Authid = JavaJDBC.getAuthId1(isbn,con);
        try{
            PreparedStatement pst1;
            PreparedStatement pst2; 
            String sql1 = "update Authors set FirstName='" + fname + "',LastName='" + lname + "' where AuthorID ='" + Authid + "'";
            pst1 = con.prepareStatement(sql1);
            pst1.execute();
            String sql2 = "update Titles set  Title='" + title + "',EditionNumber='" + edition + "',Copyright='" + cr + "' where ISBN= '" + isbn + "'";
            pst2 = con.prepareStatement(sql2);
            pst2.execute();
            pst1.close();
            pst2.close();
            System.out.println("Updated successfully!!");
            return "Updated successfully!!";
        }
        catch(Exception e){
            return "updatefailed";
            //JOptionPane.showMessageDialog(null, e);
        }
    }
    
 //Delete
    public String Delete(String fname, String lname,String isbn, String title, String edition, String cr, Connection con)
    {
        try{ 
            PreparedStatement pst1;
            PreparedStatement pst2;
            PreparedStatement pst3;
            int Authid = JavaJDBC.getAuthId1(isbn,con);
            String sql3 = "DELETE FROM AuthorISBN WHERE AuthorID=" + Authid +" and ISBN=" + isbn + "";
            pst3 = con.prepareStatement(sql3);
            pst3.execute();
            String sql1 = "DELETE FROM Authors WHERE AuthorID= ?";
            pst1 = con.prepareStatement(sql1);
            pst1.setInt(1, Authid);
            pst1.execute();
            String sql2 = "DELETE FROM Titles WHERE ISBN= ?";
            pst2 = con.prepareStatement(sql2);
            pst2.setString(1, isbn);
            pst2.execute();
            pst3.close();
            pst1.close();
            pst2.close();
            System.out.println("Deleted successfully!!");
            return ("Deleted successfully!!");
                
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
            return "This author is foriegnkey in AuthorISBN.";
        }
    }
    //Search
    public static ArrayList<Table_Fields> getList(Connection con)
    {
        ArrayList<Table_Fields> userList = new ArrayList<Table_Fields>();
        String query = "Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN";
        Statement st;
        ResultSet rs;
       try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Table_Fields User;
            while(rs.next())
            {
                User = new Table_Fields(rs.getInt("AuthorID"),rs.getString("FirstName"), rs.getString("LastName"),rs.getString("ISBN"),rs.getString("Title"),rs.getString("EditionNumber"),rs.getString("Copyright"));
                userList.add(User);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }
    public static ResultSet searchA(String columnname, String columnvalue, Connection con ){
            try{
                    String sql="Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE A."+columnname+"=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN";
                    PreparedStatement pst1 = con.prepareStatement(sql);
                    System.out.println(columnvalue);
                    pst1.setString(1,columnvalue);

                    ResultSet rs2 = pst1.executeQuery();

                    return rs2;

            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
                    return null;
            }

    }
public static ResultSet Searchfn(String tb,Connection con){
   try {
                    String query = "select * from Authors where FirstName='" + tb.trim() + "'";
                    PreparedStatement ps = con.prepareStatement(query);		
                    ResultSet rs = ps.executeQuery();				
                    return rs;
            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
                    return null;
            }

    }
    public static ResultSet Searchfnn(String tb,Connection con){
   try {
                    PreparedStatement ps=con.prepareStatement("Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE A.FirstName=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN");
                    ps.setString(1,tb);        
                    ResultSet rs =ps.executeQuery(); 
                    return rs;
            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
                    return null;
            }

    }
    public static ResultSet Searchln(String tb,Connection con){
   try {
                    String query = "select * from Authors where LastName='" + tb.trim() + "'";
                    PreparedStatement ps = con.prepareStatement(query);		
                    ResultSet rs = ps.executeQuery();				
                    return rs;
            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
                    return null;
            }

    }
    public static ResultSet Searchlnn(String tb,Connection con){
   try {
                    PreparedStatement ps=con.prepareStatement("Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE A.LastName=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN");
                    ps.setString(1,tb);        
                    ResultSet rs =ps.executeQuery(); 
                    return rs;
            }
            catch(Exception e)
            {
                    JOptionPane.showMessageDialog(null, e);
                    return null;
            }

	}
         public static ResultSet Searchisbn(String tb,Connection con){
       try {
                        String query = "select * from Titles where ISBN='" + tb.trim() + "'";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchisbnn(String tb,Connection con){
       try {
                        PreparedStatement ps=con.prepareStatement("Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE T.ISBN=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN");
			ps.setString(1,tb);        
                        ResultSet rs =ps.executeQuery(); 
                        return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchyt(String year,String stitle,Connection con){
       try {
                        String query = "select * from Titles where Title='" + stitle.trim() + "' and Copyright='"+year.trim()+"'";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchytn(String year,String stitle,Connection con){
       try {
                        PreparedStatement ps=con.prepareStatement("Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE T.Title=? and T.Copyright>=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN");
			ps.setString(1,stitle);
                        ps.setString(2,year);
                        ResultSet rs =ps.executeQuery(); 
                        return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
     public static ResultSet Searchfn1(Connection con){
       try {
			String query = "Select AuthorID,FirstName from s_bgalla.Authors";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn2(Connection con){
       try {
			String query = "Select AuthorID,LastName from s_bgalla.Authors";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn3(Connection con){
       try {
			String query = "Select FirstName,LastName from s_bgalla.Authors";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn4(Connection con){
       try {
			String query = "Select ISBN,Title,EditionNumber from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn5(Connection con){
       try {
			String query = "Select ISBN,Title,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn6(Connection con){
       try {
			String query = "Select Title,EditionNumber,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn7(Connection con){
       try {
			String query = "Select ISBN,EditionNumber,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn8(Connection con){
       try {
			String query = "Select ISBN,Title from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn9(Connection con){
       try {
			String query = "Select ISBN,EditionNumber from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn10(Connection con){
       try {
			String query = "Select ISBN,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn11(Connection con){
       try {
			String query = "Select Title,EditionNumber from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn12(Connection con){
       try {
			String query = "Select Title,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
  public static ResultSet Searchfn13(Connection con){
       try {
			String query = "Select EdutionNumber,Copyright from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
public static ResultSet Searchfn14(Connection con){
       try {
			String query = "Select * from s_bgalla.Authors";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
public static ResultSet Searchfn15(Connection con){
       try {
			String query = "Select * from s_bgalla.Titles";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
    public static ResultSet Searchfn16(Connection con){
       try {
			String query = "Select * from s_bgalla.AuthorISBN";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchfn17(Connection con){
       try {
			String query = "Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchbt( String tableName1,String stitle, Connection con){
       try {
			String query = "select * from " + tableName1.trim() + " where title='" + stitle.trim() + "'";
			PreparedStatement ps = con.prepareStatement(query);		
			ResultSet rs = ps.executeQuery();				
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
        public static ResultSet Searchbtn(String stitle, Connection con){
       try {            
                        PreparedStatement ps=con.prepareStatement("Select A.AuthorID,FirstName,LastName,T.ISBN,Title,EditionNumber,Copyright from `Authors` as A, `Titles` as T,`AuthorISBN` WHERE T.Title=? and A.AuthorID=AuthorISBN.AuthorID and T.ISBN=AuthorISBN.ISBN");
			ps.setString(1,stitle);        
                        ResultSet rs =ps.executeQuery(); 			
			return rs;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}


}
