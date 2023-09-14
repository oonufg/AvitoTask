package ru.Pavel.Domain.Entities;

public class Segment {
    private long id;
    private String slug;
    public Segment(long id, String slug){
        this.slug = slug;
        this.id = id;
    }
    public String getSlug(){
        return this.slug;
    }

    public long getId(){
        return id;
    }

}
