/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author dell
 */
public class dbConnection implements Serializable {

    private String username, password;

    public dbConnection() {
        username = null;
        password = null;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public  Connection Connectdb() 
    {
        try {
            String host="localhost";
            String dbname="s_bgalla";
            String url="jdbc:mysql://" + host +"/"+ dbname+ "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, username, password);
            return conn;
        } catch (ClassNotFoundException e) {
                 return null;

        } catch (SQLException ex) {
                  return null;
        }


    }
}



