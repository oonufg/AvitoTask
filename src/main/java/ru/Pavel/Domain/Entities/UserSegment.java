package ru.Pavel.Domain.Entities;

import java.util.Date;

public class UserSegment extends Segment{
    private Date additionDate;
    private String action;
    public UserSegment(long id, String slug,String action ,long timestamp){
        super(id, slug);
        this.action = action;
        this.additionDate = new Date(timestamp);
    }
    public String getAdditionDate(){
        return additionDate.toString();
    }
}
