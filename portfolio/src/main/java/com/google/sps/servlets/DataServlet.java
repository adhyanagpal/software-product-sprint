// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import com.google.gson.Gson;
import java.util.*;
import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    
    //public ArrayList<mycomments> comments=new ArrayList();
    /*
    comments.add("hey");
    comments.add("hi");
    comments.add("hello");
    */

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    Query query = new Query("mycomments").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    ArrayList<mycomments> comments=new ArrayList<>();
    
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String comm = (String) entity.getProperty("comment");
      String name = (String) entity.getProperty("name");
      String emailid= (String) entity.getProperty("email");
      String userid= (String) entity.getProperty("userid");
      long timestamp = (long) entity.getProperty("timestamp");

      mycomments nc = new mycomments(id,comm,name,userid,emailid,timestamp);

      comments.add(nc);
    }

    response.setContentType("application/json;");
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    response.getWriter().println(json);
  }

  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{

      UserService userService = UserServiceFactory.getUserService();

      // Only logged-in users can post messages
      if (!userService.isUserLoggedIn()) {
        res.sendRedirect("/");
        return;
      }

      String comm= req.getParameter("com");
      String nm=req.getParameter("name");
      String emailid=userService.getCurrentUser().getEmail();
      String userid= userService.getCurrentUser().getUserId();
      long timestamp = System.currentTimeMillis();
      
      Entity taskEntity = new Entity("mycomments");
      taskEntity.setProperty("name", nm);
      taskEntity.setProperty("comment",comm);
      taskEntity.setProperty("userid",userid);
      taskEntity.setProperty("email",emailid);
      taskEntity.setProperty("timestamp", timestamp);

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      datastore.put(taskEntity);

      res.sendRedirect("/index.html");
  }


}

