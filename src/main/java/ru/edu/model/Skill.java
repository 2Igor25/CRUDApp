package ru.edu.model;

public class Skill {

    private String descriptionSkill;
    private String name;
    private Long id;

    public void setDescriptionSkill(String descriptionSkill) {
        this.descriptionSkill = descriptionSkill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getDescriptionSkill() {

        return descriptionSkill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
