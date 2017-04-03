/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author hnaga
 */
public class JavaJDBC {

    
    public Connection getConnection()
   {
       Connection con;
       try {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");
          //con = DriverManager.getConnection("jdbc:mysql://cs99.bradley.edu/s_bgalla","s_bgalla","HiBVCSaD");
          System.out.println("Database Connected Successgully");
          JOptionPane.showMessageDialog(null, "Datebase Connected Successfully");
           return con;
       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Database not Connected");
           return null;
       }
       
   }
public static  int getAuthId(String Fname, String Lname){
		
		Connection con;
                int maxauthid = 0;
       try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");
			PreparedStatement ps = null;
			ResultSet rs = null;
                        
			if(Lname.equals("empty"))
			{
				
				String query = "Select AuthorID from s_baglla.Authors where FirstName = ?";
				ps = con.prepareStatement(query);
				ps.setString(1,Fname);
				rs = ps.executeQuery();
			}
			
			else if(Fname.equals("empty"))
			{
				
				String query = "Select AuthorID from s_baglla.Authors where LastName = ?";
				ps = con.prepareStatement(query);
				ps.setString(1,Lname);
				rs = ps.executeQuery();
			}

			else
			{
				String query = "Select AuthorID from s_bgalla.Authors where FirstName = ? and LastName = ?";
		
				ps = con.prepareStatement(query);
				ps.setString(1,Fname);
				ps.setString(2,Lname);
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				
				maxauthid = rs.getInt(1);
			}
			
			
			rs.close();
			ps.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		//System.out.println("GetAuthId by fn,ln in fun :"+maxauthid);
		
		return maxauthid;
			
	}
    //Search authorid 
    public static  int getAuthId(){
		Connection con;
		int maxauthid = 0;
		 try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");
			
			String query = "Select * from s_bgalla.Authors";
				
			PreparedStatement ps = con.prepareStatement(query);
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
		System.out.println("GetAuthId :"+maxauthid);
		
		return maxauthid;
			
	}
//Search isbn by title
	public static  int getISBN(String Title){
		
		int ISBN = 0;
                Connection con;
		try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");			
			
			String query = "Select ISBN from s_bgalla.titles where title = ?";
				
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,Title);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ISBN = rs.getInt(1);
			
			}
			rs.close();
			ps.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		//System.out.println("GetISBN :"+ISBN);
		
		return ISBN;
			
	}
        //Search author id by isbn
	public static  int getAuthId(String ISBN){
            
		Connection con;
		int maxauthid = 0;
		try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");			
			String query = "Select AuthorID from s_bgalla.AuthorISBN where ISBN = ?";
				
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,ISBN);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				maxauthid = rs.getInt(1);
//				return maxauthid;
			}
			rs.close();
			ps.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		//System.out.println("GetAuthId :"+maxauthid);
		
		return maxauthid;
			
	}
        //To know how many isbn are there for a author
	public static  int getISBN_count(String ISBN){
		Connection con;
		int ISBN_count = 0;
		try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");			
			String query1 = "SELECT ISBN FROM authorisbn where ISBN = ?";				
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setString(1,ISBN);
			ResultSet rs = ps.executeQuery();
			//int i = 0;
			while(rs.next())
			{
				ISBN_count = rs.getInt(1);
			
			}
			rs.close();
			ps.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
                //System.out.println("getISBN_count in fun:"+ISBN_count);
		return ISBN_count;
			
	}
        //search isbn by authorid
	public static  int[] getISBN(int Authid){
		int [] ISBN = new int[100];
		 Connection con;
		try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");			
			
			String query = "Select ISBN from s_bgalla.AuthorISBN where AuthorID = ?";
				
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,Authid);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next())
			{
				ISBN[i ++] = rs.getInt(1);
			
			}
			rs.close();
			ps.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		int j = 0;
		while(true)
		{
			//System.out.println("GetISBN :"+ISBN[j ++]);
			if (ISBN [j] == 0)
				break;
		}
		
		return ISBN;
			
	}
        //Insert Function
        public static  void Insert(String [] Input){
		Connection con;
                try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root"); 
                       	String ISBN = Input[0];
			String EditionNumber = Input[1];
			String Copyright = Input[2];
                        String Title = Input[3];
                        String FirstName = Input[4];
			String LastName = Input[5];
			
			String column[] = new String[10];
			int i = 0;
			for (String name:Input)
			{
				column[i] = name;
				System.out.println("values in string array I:"+i+" "+column[i++]);
				
			}
			String query1 = "insert into s_bgalla.authors(FirstName, LastName) values(?, ?)";
			
			System.out.println(query1);
			PreparedStatement ps2 = con.prepareStatement(query1);
			ps2.setString(1,column[4]);
			ps2.setString(2,column[5]);
                        
			String query2 = "insert into s_bgalla.titles(ISBN,EditionNumber,Copyright,TITLE) values(?,?,?,?)";
			System.out.println(query2);
			PreparedStatement ps1 = con.prepareStatement(query2);
			ps1.setString(1,column[0]);
			ps1.setString(2,column[1]);
			ps1.setString(3,column[2]);
			ps1.setString(4,column[3]);
			
			
			int ISBN_new = JavaJDBC.getISBN(Title);
				if(ISBN_new == 0)
				{
					ps1.execute();
					ps1.close();
				}
			int curr_id = JavaJDBC.getAuthId(FirstName, LastName);
				if(curr_id == 0)
				{
					ps2.execute();
					ps2.close();
					String query3 = "insert into s_bgalla.authorisbn(AuthorID, ISBN) values(?,?)";
				
					PreparedStatement ps3 = con.prepareStatement(query3);
						int Auth_id = JavaJDBC.getAuthId();
						ps3.setInt(1, Auth_id);
						if(ISBN_new == 0)
						{
							ps3.setString(2,ISBN);
						}
						else if(ISBN_new != 0)
						{
							ps3.setInt(2,ISBN_new);
						}
						ps3.execute();
						ps3.close();
                                }
				
				else if(curr_id != 0)
				{
					
					String query3 = "Insert into s_bgalla.AuthorISBN(AuthorID, ISBN) values(?,?)";
				
					PreparedStatement ps3 = con.prepareStatement(query3);
						int Auth_id = JavaJDBC.getAuthId(FirstName, LastName);
						ps3.setInt(1, Auth_id);
						if(ISBN_new == 0)
						{
							ps3.setString(2,ISBN);
						}
						else if(ISBN_new != 0)
						{
							ps3.setInt(2,ISBN_new);
						}
						ps3.execute();
						ps3.close();	
				}		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}

	}
        //Update function
	public static  void Update (String input[]){
                int ISBN_count = 0;
                        String AuthorID = input[0];
                        String ISBN = input[1];
                        String Title = input[2];
			String EditionNumber = input[3];
			String Copyright = input[4];
                        String FirstName = input[5];
			String LastName = input[6];
		Connection con;
                try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root");
				
			if(input.length == 3)
			{
				System.out.println("Length is 3");
				int i = 0;
				String column[] = new String[10];
				for (String name:input)
				{
					column[i] = name;
					System.out.println("values in string array I:"+i+" "+column[i++]);
					
				}
				
				if(column[0].equals("Copyright")||column[0].equals("EditionNumber")||column[0].equals("Title"))
				{
					
					String query = "UPDATE titles set "+column[0]+"  =?"+" where ISBN = ?";
					System.out.println("Query"+query);
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, column[1]);
					ps.setString(2, column[2]);
					ps.execute();
				}
				else
				{
					int Authid = JavaJDBC.getAuthId(ISBN);
                                        System.out.println("ISBN:"+ISBN);
					System.out.println("AuthorId:"+Authid);
					String query = "UPDATE authors set "+column[0]+" =?"+" where AuthorID = ?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, column[1]);
					ps.setInt(2, Authid);
					ps.execute();
				}
			}
			else
			{
			int i = 0;
			String column[] = new String[10];
			for (String name:input)
			{
				column[i] = name;
				System.out.println("values in string array I:"+i+" "+column[i++]);
			}
			
			String query1 = "UPDATE titles set Title = ?,EditionNumber = ?,Copyright = ? where ISBN = ?";
                        
                        PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setString(1,column[2]);
			ps1.setString(2,column[3]);
			ps1.setString(3,column[4]);
			ps1.setString(4,column[1]);
			ps1.execute();
			
			int Authid = JavaJDBC.getAuthId(ISBN);//getting authorid by isbn
//			System.out.println("AuthorId:"+Authid);
//                        System.out.println("ISBN:"+ISBN);
			String query2 = "UPDATE authors set  FirstName = ?,LastName = ? where AuthorID = ?";
			
			PreparedStatement ps2 = con.prepareStatement(query2);
			ps2.setString(1,column[5]);
			ps2.setString(2,column[6]);
			ps2.setInt(3, Authid);
			ps2.execute();
			ps1.close();
			ps2.close();
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	
			
	}
//delete function
	public static void Delete(String [] Input){
		Connection con;
                        String AuthorID = Input[0];
                        String ISBN = Input[1];
                        String Title = Input[2];
			String EditionNumber = Input[3];
			String Copyright = Input[4];
                        String FirstName = Input[5];
			String LastName = Input[6];
                        System.out.println("Author ID:"+AuthorID);
                        System.out.println("ISBN:"+ISBN);
                        System.out.println("Title:"+Title);
                try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s_bgalla","root","root"); 	
		String query1 = "delete from authorisbn where ISBN = ?";
		PreparedStatement pst1 = con.prepareStatement(query1);
		String query2 = "delete from titles where Title = '"+ Title + "'  ";
		PreparedStatement pst2 = con.prepareStatement(query2);
		int AuthorId = 0, ISBN_count = 0;
		
		String query3 = "delete from authors where AuthorId = ?";
		PreparedStatement pst3 = con.prepareStatement(query3);
		ISBN_count = JavaJDBC.getISBN_count(ISBN);
		//System.out.println("ISBN_count :"+ISBN_count);
		AuthorId = JavaJDBC.getAuthId(FirstName, LastName);
		if(ISBN_count == 1)
		{
			pst2.execute();
			pst2.close();
		}
		else
		{
                        pst1.setString(1, ISBN);
			pst1.execute();
			pst1.close();
		}
                
		int [] ISBN_new = JavaJDBC.getISBN(AuthorId);
		pst3.setInt(1, AuthorId);
		//System.out.println("ISBN_new:"+ISBN_new[0]);
                
		if(ISBN_new[0] == 0)
		{
			System.out.println("This author has no Titles");
			int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
			if(confirm == 0)
			{
				pst3.execute();
				pst3.close();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "This authors has Titles and cannot be deleted ");
		}
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
	}

}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//       String[] input={"0131313131","3","2003","Data Structures","Bhavana","Galla"};
//       Insert(input);
//       String[] input={"6","0131313131","Mobile Computing","4","2006","Himaja","Galla"};
//       Update(input);
//         String[] input={"6","0131313131","Mobile Computing","4","2006","Himaja","Galla"};
//         Delete(input);
//         String[] input={"6","0131313131","Mobile Computing","4","2006","Himaja","Galla"};
//         Delete(input);
    }
    
}
