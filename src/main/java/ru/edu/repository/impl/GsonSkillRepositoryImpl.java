package ru.edu.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.edu.model.Skill;
import ru.edu.repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

   private final Path skillsFilePath = Path.of("src", "main", "resources", "skill.json");
   private final Gson GSON = new Gson();

   private List<Skill> getAllSkills () {
       try {
           Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
           return GSON.fromJson(Files.readString(skillsFilePath), targetClassType);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return Collections.emptyList();
   }

   private void writeSkillsToFile (List<Skill> skills) {
           skills.stream().forEach(s-> {
               try {
                   Files.writeString(skillsFilePath, GSON.toJson(s));
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
   }

   private Long generateNewMaxId(List<Skill> skills) {
        Skill skillWithMaxID = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxID) ? skillWithMaxID.getId()+1 : 1l;
   }
    
    @Override
    public Skill getById(Long id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Skill add(Skill item) {
       List<Skill> currentSkill = getAllSkills();
       item.setId(generateNewMaxId(currentSkill));

       currentSkill.add(item);
       writeSkillsToFile(currentSkill);

       return item;
    }

    @Override
    public void delete(Long id) {
        List<Skill> currentSkill = getAllSkills();
        currentSkill.removeIf(s -> s.getId().equals(id));

        writeSkillsToFile(currentSkill);
    }

    @Override
    public Skill update(Skill updateSkill) {
        List<Skill> currentSkill = getAllSkills();

        currentSkill.forEach(skill -> {
            if(skill.getId().equals(updateSkill.getId())) {
                skill.setName(updateSkill.getName());
                skill.setDescriptionSkill(updateSkill.getDescriptionSkill());
            }
        });

        writeSkillsToFile(currentSkill);
        return updateSkill;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkills();
    }
}
