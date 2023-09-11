package ru.Pavel.Domain.Entities;

public class Segment {
    private String slug;
    public Segment(String slug){
        this.slug = slug;
    }
    public String getSlug(){
        return this.slug;
    }
}
