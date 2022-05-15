package ru.edu.model;

public class Specialty {

    private Long id;

    private String descriptionSpecialty;

    private String name;

    public String getDescriptionSpecialty() {
        return descriptionSpecialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescriptionSpecialty(String descriptionSpecialty) {
        this.descriptionSpecialty = descriptionSpecialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
