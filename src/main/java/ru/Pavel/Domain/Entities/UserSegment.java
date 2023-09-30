package ru.Pavel.Domain.Entities;

import java.util.Date;

public class UserSegment extends Segment{
    private Date additionDate;
    private Date expiredDate;
    private String action;
    public UserSegment(long id, String slug,String action ,long additionTimestamp, long expireTimeStamp){
        super(id, slug);
        this.action = action;
        this.additionDate = new Date(additionTimestamp);
        this.expiredDate = new Date((expireTimeStamp));
    }
    public String getAdditionDate(){
        return additionDate.toString();
    }
    public String getExpiredDate(){
        return additionDate.toString();
    }
}
