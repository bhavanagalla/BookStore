/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author hnaga
 */
public class Table_Fields {
    private  int AuthorID;
    private  String FirstName;
    private  String LastName;
    private  String ISBN;
    private  int EditionNumber;
    private  String Title;
    private  String Copyright;
    
    public Table_Fields(int AuthorID, String FirstName, String LastName,String ISBN,String Title,int EditionNumber,String Copyright)
    {
        this.AuthorID = AuthorID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ISBN=ISBN;
        this.Title=Title;
        this.EditionNumber=EditionNumber;
        this.Copyright=Copyright;
       
    }

    public int getAuthorID()
    {
        return AuthorID;
    }
    public String getFirstName()
    {
        return FirstName;
    }
    public String getLastName()
    {
        return LastName;
    }
    public String getISBN()
    {
        return ISBN;
    }
    public String getTitle()
    {
        return Title;
    }
    public int getEditionNumber()
    {
        return EditionNumber;
    }
    public String getCopyright()
    {
        return Copyright;
    }

}
