package ru.edu.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.edu.model.Skill;
import ru.edu.repository.SkillRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonSkillRepositoryImpl implements SkillRepository {

    List<String> skills = new ArrayList<>();
    Gson gson = new Gson();
    
    @Override
    public Skill getById(Long id) throws Exception {
        for (String skill: skills) {
            Skill skillById = new Gson().fromJson(skill, Skill.class);
            if(skillById.getId() == id) {
                return skillById;
            }
        }
        return null;
    }

    @Override
    public void add(Skill item) throws Exception {
        skills.add(gson.toJson(item));
    }

    @Override
    public void delete(Long id) throws Exception {

        String json = skills.toString();
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> list = new Gson().fromJson(json, targetClassType);

        for (Skill skill: list) {
            if(skill.getId() == id) {
                list.remove(skill);
                skills = Collections.singletonList(gson.toJson(list));
            }
        }
    }

    @Override
    public void update(Long id) throws Exception {

    }

    @Override
    public void save(Long id) {

    }

    @Override
    public List<Skill> getAll() throws Exception {

        String json = skills.toString();
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> list = new Gson().fromJson(json, targetClassType);
        return list;
    }
}
