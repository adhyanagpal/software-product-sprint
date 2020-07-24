package com.google.sps.servlets;

import com.google.gson.Gson;
import java.util.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/isloggedin")
public class LoginStatus extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");

    UserService userService = UserServiceFactory.getUserService();

    loginStatus myresponse=new loginStatus();
    
    if (userService.isUserLoggedIn()) {
      myresponse.isloggedin=true;
      String urlToRedirectToAfterUserLogsOut = "/";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

      myresponse.url=logoutUrl;
      
    } else {

      myresponse.isloggedin=false;
      String urlToRedirectToAfterUserLogsIn = "/";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
      myresponse.url=loginUrl;
      
    }
    
    Gson gson = new Gson();
    String json = gson.toJson(myresponse);
    response.getWriter().println(json);

  }
}

class loginStatus{
    boolean isloggedin;
    String url;
}
