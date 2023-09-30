package ru.Pavel.Domain.Persistance.Repositories.Enums;

public enum ActionsWithSegments {
    ADDING("adding"),
    REMOVAL("removal");

    private String title;
    ActionsWithSegments(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

}
