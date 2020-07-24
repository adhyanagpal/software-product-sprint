package com.google.sps.servlets;
import java.util.*;

public class mycomments{
    private long id;
    private String comment;
    private String name;
    private String email;
    private String userid;
    private long timestamp;

    public mycomments(long id, String c, String n, String u, String e, long timestamp){
        this.id=id;
        this.comment=c;
        this.name=n;
        this.userid=u;
        this.email=e;
        this.timestamp=timestamp;
    }
}