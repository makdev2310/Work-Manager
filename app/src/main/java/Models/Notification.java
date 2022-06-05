package Models;

import java.util.Date;

public class Notification {
    public String title ;
public String content ;
public Date create_at;
public String priority;
public int view;

public Notification(String content, Date create_at, String priority, int view, String title){
    this.content=content;
    this.create_at=create_at;
    this.priority=priority;
    this.view=view;
    this.title=title;
}
String getContent(){
    return this.content;
}
Date getcreate_at(){
        return this.create_at;
    }
String getpriority(){
    return this.priority;
}
int getview(){
        return this.view;
    }
    String getTitle(){
        return this.title;
    }
}


