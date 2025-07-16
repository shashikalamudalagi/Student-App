package in.pentagon.studentapp.servlets; 
 
import java.io.IOException; 
import java.io.PrintWriter; 
 
import in.pentagon.studentapp.dao.StudentDAO; 

import in.pentagon.studentapp.dao.StudentDAOImpl; 
import in.pentagon.studentapp.dto.Student; 
import jakarta.servlet.RequestDispatcher; 
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebFilter; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 
@WebServlet("/forgotpassword") 
public class Forgotpassword extends HttpServlet{ 
 @Override 
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
ServletException, IOException { 
  StudentDAO sdao=new StudentDAOImpl(); 
  PrintWriter out=resp.getWriter(); 
  Student 
s=sdao.getStudent(Long.parseLong(req.getParameter("phone")),req.getParameter("mail")); 
  if(s!=null) { 
   if(req.getParameter("password").equals(req.getParameter("confirm"))) { 
    s.setPassword(req.getParameter("password")); 
    boolean res=sdao.updateStudent(s); 
    if(res) { 
     //out.println("<h1>Password updated successfully!</h1>"); 
     req.setAttribute("success","Password updated successfully!"); 
     RequestDispatcher rd=req.getRequestDispatcher("login.jsp"); 
  

     rd.forward(req, resp); 
    } 
    else { 
     req.setAttribute("error","Failed to update the password!"); 
     RequestDispatcher 
rd=req.getRequestDispatcher("forgotpassword.jsp"); 
     rd.forward(req, resp); 
    } 
   }else { 
    req.setAttribute("error","Password mismatch!"); 
    RequestDispatcher rd=req.getRequestDispatcher("forgotpassword.jsp"); 
    rd.forward(req, resp); 
   } 
  } 
  else { 
   req.setAttribute("error","Student not found!"); 
   RequestDispatcher rd=req.getRequestDispatcher("forgotpassword.jsp"); 
   rd.forward(req, resp); 
  } 
 } 
} 