package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/curuserid")
public class LoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    String userid="";

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      
      userid = userService.getCurrentUser().getUserId();
      
    } 
    
    response.getWriter().println(userid);
  }
}
