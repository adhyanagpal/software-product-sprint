package com.google.sps.servlets;
import java.util.*;

public class mycomments{
    private long id;
    private String comment;
    private String name;
    private long timestamp;

    public mycomments(long id, String c, String n, long timestamp){
        this.id=id;
        this.comment=c;
        this.name=n;
        this.timestamp=timestamp;
    }
}