package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query= "insert into bookdata(BOOKNAME,BOOKEDITION,BOOKPRICE)VALUES(?,?,?)";
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
               //get printWriter
    	  PrintWriter pw = res.getWriter();
    	  //set content type
    	  res.setContentType("text/html");
    	  //Get the book information
    	  String bookName = req.getParameter("bookname");
    	  String bookEdition = req.getParameter("bookEdition");
    	  float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
    	  
    	  
    	  //Load jdbc driver
    	  try {
    		  Class.forName("com.mysql.cj.jdbc.Driver");
    	  }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	  //Generate the connection 
    	  try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/book","root","123456789");	  
    		  PreparedStatement ps = con.prepareStatement(query);){
    		  ps.setString(1, bookName);
    		  ps.setString(2, bookEdition);
    		  ps.setFloat(3, bookPrice);
    		  int count = ps.executeUpdate();
    		  if(count ==1) {
    			  pw.println("<h2>Record is Registerd Successfully");
    			  pw.println("<br>");
    			  	  }
    		  else
    		  {
    			  pw.println("<h2>Record is Not Registerd Successfully");
    		  }
    	  }catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+ se.getMessage()+ "</h1>");
		}catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+ e.getMessage()+ "</h1>");
		}
    	  pw.println("<a href='home3.html'>Home</a>");
    	  pw.println("<br>");
    	  pw.println("<a href='BookList'>Book List</a>");
    }
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }
}
