<%-- 
    Document   : index
    Created on : Aug 5, 2016, 1:22:49 PM
    Author     : dell
--%>

<%@page import="java.JavaJDBC"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store Database</title>
        <link rel="stylesheet" href="css/stylex.css" type="text/css" />
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <style>
            #insert{display: block;}
            #Modify{display: none;}
            #Dele{display: none;}
            #Search{display: none;}
           
        </style>
        <script src="javascript/prototype.js" type="text/javascript"></script>
        <script src="javascript/effects.js" type="text/javascript"></script>
        <script src="javascript/controls.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/jquery.loginfocus.js"></script>
        <script type="text/javascript" src="js/jquery_div.js"></script>
        <script type="text/javascript">
function SwapDivsWithClick(div1,div2,div3,div4)
{
   d1 = document.getElementById(div1);
   d2 = document.getElementById(div2);
   d3 = document.getElementById(div3);
   d4 = document.getElementById(div4);
   if( d2.style.display == "none")
   {
      d1.style.display = "none";
      d2.style.display = "block";
      d3.style.display = "none";
      d4.style.display = "none";
      
   }
   else
   {
      d1.style.display = "block";
      d2.style.display = "none";
      d3.style.display = "none";
      d4.style.display = "none";
   }
}
</script>


    </head>
    <body>
   
        <jsp:useBean id="dbfunc" scope="session" class="java.dbConnection" />
        <jsp:setProperty name="dbfunc" property="username" />
        <jsp:setProperty name="dbfunc" property="password"/>
        <%
                Connection conn=null;
                String username, password;
                username = dbfunc.getUsername();
                password = dbfunc.getPassword();
                
                String msg = request.getParameter("msg");
                msg = (msg == null ? "" : msg);
                if(request.getParameter("log")!=null){
                            try {
                                    request.getSession(true);
                                    session.setAttribute("LoginForm[username]", username);
                                    session.setAttribute("LoginForm[password]", password);//out.println(username);
                                    session.setAttribute("conn", conn);
                                    conn = dbfunc.Connectdb();

                                    if (conn != null) {
                                        msg="Login Successfull. Welcome";
                                    } else {

                                        msg="Please enter valid username/password.";
                                    }
                            } catch (Exception e) {
                                msg="Please enter valid username/password.";
                            }
                   }
                
     
            String user1 = (String) session.getAttribute("user");
            /////////////// fancy html /////////////////
                        String fancyhtml = "";
            fancyhtml += "<link rel=\"stylesheet\" href=\"css/fancydropdown.css\">";
            fancyhtml += "<div id=\"menu\">";
            fancyhtml += "<ul class=\"tabs\">";

            if (user1 != null) {

                fancyhtml += "<li class=\"hasmore\"><a href=\"#\"><span>WELCOME !!! " + user1 + "</span></a>";
                fancyhtml +="";
                fancyhtml += "</li>";
                fancyhtml += "<li class=\"last\"><a href=\"logout.jsp\"><span>(Logout)</span></a>";
            } else {
                fancyhtml += "<li class=\"hasmore\"><a href=\"#\"><span>WELCOME !!!</span></a>";
                fancyhtml += "<ul class=\"dropdown\">";
                fancyhtml += "</ul>";
                fancyhtml += "</li>";
            }

            fancyhtml += "</li>";
            fancyhtml += "</ul>";
            fancyhtml += "</div>";
            fancyhtml += "";
             out.println(fancyhtml);
            %>
                
            
    <div id="main2">
     
           <br /><br /><br /><br /><br /><h1 align="center" style="color:#fff">Book Store</h1>
           <%
          
           if (conn != null) {
                        
             %>
             <table align="center" width="100%" cellspacing="0">
			<tr>
                            <td align="center"><a href="javascript:SwapDivsWithClick('insert','Modify')" target="#insert" id="insertl"><img src="images\add.png"></a></td>
                            <td align="center"><a href="javascript:SwapDivsWithClick('Modify','insert')" target="#insert" id="updatel"><img src="images\update.png"></a></td>
                            <td align="center"><a href="javascript:SwapDivsWithClick('Dele','Modify')" target="#insert" id="deletel"><img src="images\delete.png"></a></td>
                            <td align="center"><a href="javascript:SwapDivsWithClick('Search','Dele')" target="#insert" id="searchl"><img src="images\search_boo.png"></a></td> 
                        </tr>
               </table>
             <%
                         }
            %>
    </div>
<br/><br/><br/><br/>
        <div class="main_page">            
            <div class="mid_content">
                <div class="home_inner_panel">
                    <div class="home_top_panel">
                         <%
          
           if (conn == null) {
                        
             %>
                        <div class="home_left_panel"> 
                            <div class="home_logo1">
                                <img src="images/search_book.png" alt="" align="center" height="300"/> 
                            </div>
                        </div>
                        
            
            <form id="login-form" action="index.jsp" method="get">		
                <div class="home_right_panel">
                    <div class="right_login_box">
                        <div class="login_id_box">
                            <input class="login_id_box_text_box" value="" placeholder="Username" name="LoginForm[username]" id="LoginForm_username" type="text"/><br/><br/>
                            <input class="login_id_box_text_box" value="" placeholder="Password" name="LoginForm[password]" id="LoginForm_password" type="password"/><br/><br/>
                            <div class="login_button_home"><input class="btn_login" type="submit" name="log" value="Login" />
                            </div>
                        </div>
                    </div>
                </div>                               
           </form>
                        
                         <%                            
                                     }
                        %>
                 
            
            <%
          
           if (conn != null) {
                        
             %>
             
             <div id="container">
                   <div id="insert">
                     <form name="insert" method="post"  action="dbUpdate.jsp" target="bottom1">		   
                    <h3 align="center" style="color:#FF8C00">Insert New Record</h3>            	    
                           <table align="center" border="0" cellspacing="0">
                               <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR FIRST NAME </label></td>
                                 <td><input type="text" name="ifname"  autofocus="autofocus" required="required"></td>
                                </tr>
                                <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR LAST NAME </label></td>
                                 <td><input type="text" name="ilname" required="required"></td>
                                </tr>
                                <tr>
                                 <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">ISBN NO </label></td>
                                 <td><input type="text" name="iisbn"  autofocus="autofocus" required="required"></td>
                                </tr>
                                <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">TITLE </label></td>
                                 <td><input type="text" name="ititle" required="required"></td>
                                </tr>
                                <tr>
                                 <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">EDITION NO </label></td>
                                 <td><input type="text" name="ieditionno"  autofocus="autofocus" required="required"></td>
                                </tr>
                                <tr>
                                 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">COPYRIGHT </label></td>
                                 <td><input type="text" name="icopyright" required="required"></td>
                                </tr>
                                <tr>
                                 <td align="center" valign="top" colspan="2"><br/>
                                     <input type="submit" class="btn_login" value="Insert" name="insert">
                                 <input type="reset" value="clear" name="reset">
                                 </td>
                                </tr>
                    </table> 
                 </form>
           </div>
                  <div id="Modify">
                            <form name="update" method="post"  action="dbUpdate.jsp" target="bottom1">		   
            	<h3 align="center" style="color:#FF8C00">Update Record</h3>            	    
		   <table align="center" border="0" cellspacing="0">
			<tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR FIRST NAME </label></td>
			 <td><input type="text" name="ufname"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR LAST NAME </label></td>
			 <td><input type="text" name="ulname" required="required"></td>
                        </tr>
			<tr>
                         <tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">ISBN NO </label></td>
			 <td><input type="text" name="uisbn"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">TITLE </label></td>
			 <td><input type="text" name="utitle" required="required"></td>
                        </tr>
			<tr>
                         <tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">EDITION NO </label></td>
			 <td><input type="text" name="ueditionno"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">COPYRIGHT </label></td>
			 <td><input type="text" name="ucopyright" required="required"></td>
                        </tr>
			<tr>
			 <td align="center" valign="top" colspan="2"><br/>
                         <input type="submit" class="btn_login" value="Update" name="update">
                         <input type="reset" value="clear" name="reset">
                         </td>
                	</tr>
                    </table> 
        </form>
                  </div>
                 
                  <div id="Search">
                      <form method="post" action="dbQuery.jsp" target="bottom1">
                            <h3 align="center" style="color:#FF8C00">Search Database</h3>            	    
                            <table border="0" cellspacing="0">
                                <tr><td style="text-align: left"><label for="testinput"><font color='white'>Search books by</font></label></td></tr>
                                <tr><td> <div class="styled-select black rounded">
                                   <select  name="searchby">
                                    <option value="">--</option>
                                    <option value="FirstName">Author's First Name</option>
                                    <option value="LastName">Author's Last Name</option>
                                    <option value="ISBN" >ISBN</option>
                                   </select>
                                        </div></td>
                                        <td><input type="text"  name="tb" autocomplete="off" class="input1" style="background: #e4ebeb;"value=""/></td>
                             </tr></table>
                                <label for="testinput"><font color='white'>From year</font></label>
                                &nbsp;<div class="styled-select blue rounded">
                                        <select name="year">
                                                <option value="">Year</option>
                                                <script language="javascript">
                                                        for(i=1970;i<=2016;i++)
                                                                document.write("<option>"+i+"</option>");
                                                </script>
                                        </select>
                                    </div> 
                                <br>
                            <input type="text" id="search" name="search" placeholder="Search books by Title" autocomplete="off" class="input" value=""/><br /><br>
                            <label for="testinput1"><font color='white'>Select Table:</font></label><br/>
                               <div class="styled-select yellow rounded">
                                   <select  name="Selecttable">	
                                    <option value="">--</option>
                                    <option value="Authors">Authors</option>
                                    <option value="Titles" >Titles</option>
                                    <option value="AuthorISBN" >AuthorISBN</option>
                                    </select>
                                  </div>
                               <label for="testinput1"><font color='white'>Select Columns:</font></label><br />
                            <div>
                                <select name="fields"  multiple>	
                                    <optgroup label="Authors" >
                                    <option value="AuthorID" >Author ID</option>
                                    <option value="FirstName" >First Name</option>
                                    <option value="LastName">Last Name</option>
                                    </optgroup>
                                    <optgroup label="Titles" >
                                    <option value="ISBN">ISBN</option>
                                    <option value="Title" >Title</option>
                                    <option value="EditionNumber" >Edition Number</option>
                                    <option value="Copyright" >Copyright</option> 
                                    </optgroup>
                                    <optgroup label="AuthorISBN" >
                                    <option value="AuthorID" >Author ID</option>
                                    <option value="ISBN">ISBN</option>
                                    </optgroup>
                                </select>
                            </div>
                               <input type="checkbox" name="All" checked="checked"><font color='white'> Display Books Information</font><br>

                                <input type="submit" value="" name="Search" class="submit-btn"/> 
                                <input type="reset" value="clear" name="reset">
                    </form>
                  </div>
                  <div id="Dele">
                      <form name="delete" method="post"  action="dbUpdate.jsp" target="bottom1">		   
            	<h3 align="center" style="color:#FF8C00">Delete Record</h3>            	    
		   <table align="center" border="0" cellspacing="0">
			<tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR FIRST NAME </label></td>
			 <td><input type="text" name="dfname"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">AUTHOR LAST NAME </label></td>
			 <td><input type="text" name="dlname" required="required"></td>
                        </tr>
			<tr>
                         <tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">ISBN NO </label></td>
			 <td><input type="text" name="disbn"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">TITLE </label></td>
			 <td><input type="text" name="dtitle" required="required"></td>
                        </tr>
			<tr>
                         <tr>
                   	 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">EDITION NO </label></td>
			 <td><input type="text" name="deditionno"  autofocus="autofocus" required="required"></td>
                        </tr>
                        <tr>
			 <td align="left" valign="top"><label style="font-family:Verdana,sans-serif; color:#fff;font-size:14px">COPYRIGHT </label></td>
			 <td><input type="text" name="dcopyright" required="required"></td>
                        </tr>
			<tr>
			 <td align="center" valign="top" colspan="2"><br/>
                         <input type="submit" class="btn_login" value="Delete" name="delete">
                         <input type="reset" value="clear" name="reset">
                         </td>
                	</tr>
                    </table> 
        </form>
                  </div>

 
              <%                            
                             }
              %>
                   </div>
             </div>
                </div>
            </div>
</div>
<div class="bottom_icon">
    <div class="mid_content">
        <div class="mid_content_inner">
            <div class="bottom_icons_home">
                <h2 align="center"><font color="red"><%=msg%></font></h2>
            </div>
        </div>
    </div>
</div>
              <iframe name="bottom1" width="100%" height="2000" scrolling="no" frameborder="0" marginwidth="0" marginheight="0" style="overflow:hidden;"></iframe>
              </body>
</html>

