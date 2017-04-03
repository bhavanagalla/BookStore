/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author dell
 */
public class Table_Fields {
    private  int AuthorID;
    private  String FirstName;
    private  String LastName;
    private  String ISBN;
    private  String EditionNumber;
    private  String Title;
    private  String Copyright;
    
    public Table_Fields(int AuthorID, String FirstName, String LastName,String ISBN,String Title,String EditionNumber,String Copyright)
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
    public String getEditionNumber()
    {
        return EditionNumber;
    }
    public String getCopyright()
    {
        return Copyright;
    }
}
