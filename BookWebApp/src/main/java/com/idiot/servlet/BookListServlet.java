package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/BookList")
public class BookListServlet extends HttpServlet {
	private static final String query= "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata";
    @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
             //get printWriter
  	  PrintWriter pw = res.getWriter();
  	  //set content type
  	  res.setContentType("text/html");
  	 
  	  
  	  
  	  //Load jdbc driver
  	  try {
  		  Class.forName("com.mysql.cj.jdbc.Driver");
  	  }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
  	  //Generate the connection 
  	  try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/book","root","123456789");	  
  		  PreparedStatement ps = con.prepareStatement(query);){
  		  
  		  ResultSet rs = ps.executeQuery();
  		  pw.println("<table border='1' align = 'center'>");
  		  pw.println("<tr>");
  		  pw.println("<td>Book Id </td>");
  		  pw.println("<td>Book Name </td>");
  		  pw.println("<td>Book Edition </td>");
  		  pw.println("<td>Book Price </td>");
  		  pw.println("<td>Edit </td>");
  		  pw.println("<td>Delete </td>");
  		  pw.println("</tr>");
  		 
  		  while(rs.next())
  		  {
  			  pw.println("<tr>");  
  			  pw.println("<td>"+rs.getInt(1)+"</td>");
  	  		  pw.println("<td>"+rs.getString(2)+"</td>");
  	  		  pw.println("<td>"+rs.getString(3)+"</td>");
  	  		  pw.println("<td>"+rs.getFloat(4)+"</td>");
  	  		  pw.println("<td><a href='editScreen? id="+rs.getInt(1)+"'>Edit</a></td>");
  	  		  pw.println("<td><a href='deleteurl? id="+rs.getInt(1)+"'>Delete</a></td>");
  	  		  pw.println("</tr>");
  	  		  
  		  }
  		 pw.println("</table>");
  	  }catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+ se.getMessage()+ "</h1>");
		}catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+ e.getMessage()+ "</h1>");
		}
  	pw.println("<a href='home3.html'>Home</a>");
  }
    @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  	doGet(req,res);
  }
}
