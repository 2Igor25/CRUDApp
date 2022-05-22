package ru.edu.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.edu.model.Skill;
import ru.edu.model.Specialty;
import ru.edu.repository.SpecialtyRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

   private final Path specialtiesFilePath = Path.of("src", "main","resources", "specialty.json");
   private final Gson GSON = new Gson();

   private List<Specialty> getAllSpecialty () {
       List<Specialty> specialties = new ArrayList<>();
       try {
           Files.readAllLines(specialtiesFilePath).stream().forEach(specialty -> specialties.add(GSON.fromJson(specialty, Specialty.class)));
       } catch (IOException e) {
           e.printStackTrace();
       }

       return specialties;
   }

   private void writeSpecialtiesToFile (List<Specialty> specialties) {
       try {
           Files.writeString(specialtiesFilePath, GSON.toJson(specialties));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   private Long generateNewMaxId (List<Specialty> specialties) {
       Specialty specialtyWithMaxId = specialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
       return Objects.nonNull(specialties) ? specialtyWithMaxId.getId()+1 : 1l;
   }
    @Override
    public Specialty getById(Long id) {
        return getAllSpecialty().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Specialty add(Specialty item) {
        List<Specialty> currentSpecialty = getAllSpecialty();
        item.setId(generateNewMaxId(currentSpecialty));

        currentSpecialty.add(item);
        writeSpecialtiesToFile(currentSpecialty);

        return item;
    }

    @Override
    public void delete(Long id) {
        List<Specialty> currentSpecialty = getAllSpecialty();
        currentSpecialty.removeIf(s -> s.getId().equals(id));

        writeSpecialtiesToFile(currentSpecialty);
    }

    @Override
    public Specialty update(Specialty updateSpecialty) {
        List<Specialty> currentSpecialty = getAllSpecialty();

        currentSpecialty.forEach(specialty -> {
            if(specialty.getId().equals(updateSpecialty.getId())) {
                specialty.setName(updateSpecialty.getName());
                specialty.setDescriptionSpecialty(updateSpecialty.getDescriptionSpecialty());
            }
        });

        writeSpecialtiesToFile(currentSpecialty);
        return updateSpecialty;
    }


    @Override
    public List<Specialty> getAll(){
       return getAllSpecialty();
    }
}
