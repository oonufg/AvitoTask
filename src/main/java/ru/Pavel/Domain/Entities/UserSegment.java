package ru.Pavel.Domain.Entities;

import java.util.Date;

public class UserSegment extends Segment{
    private Date additionDate;
    private Date expiredDate;
    private String action;
    public UserSegment(long id, String slug,String action ,long additionTimestamp, Long expireTimeStamp){
        super(id, slug);
        this.action = action;
        this.additionDate = new Date(additionTimestamp);
        this.expiredDate = new Date((expireTimeStamp));
    }

    private UserSegment(UserSegmentBuilder builder){
        super(builder.id, builder.slug);
        this.additionDate = builder.additionDate;
        this.expiredDate = builder.expiredDate;
        this.action = builder.action;
    }

    public String getAdditionDate(){
        return additionDate.toString();
    }
    public String getExpiredDate(){
        return additionDate.toString();
    }

    public static class UserSegmentBuilder{
        private long id;
        private String slug;
        private String action;
        private Date additionDate;
        private Date expiredDate;

        public UserSegmentBuilder(long id, String slug,String action ,Long additionTimestamp){
            this.id = id;
            this.slug = slug;
            this.action = action;
            this.additionDate = new Date(additionTimestamp);

        }

        public UserSegmentBuilder setExpiredDate(Long expiredDate) {
            this.expiredDate = new Date(expiredDate);
            return this;
        }
        public UserSegment build(){
            UserSegment segment = new UserSegment(this);
            return segment;
        }
    }
}

