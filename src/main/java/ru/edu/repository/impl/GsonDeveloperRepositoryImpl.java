package ru.edu.repository.impl;

import com.google.gson.Gson;
import ru.edu.model.Developer;
import ru.edu.repository.DeveloperRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final Path developersFilePath = Path.of("src", "main", "resources", "developer.json");
    private final Gson GSON = new Gson();

    private List<Developer> getAllDevelopers () {

        List<Developer> developers = new ArrayList<>();
        try {
            Files.readAllLines(developersFilePath).stream().forEach(developer -> developers.add(GSON.fromJson(developer, Developer.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }

    private void writeDevelopersToFile (List<Developer> developers) {
        try {
            Files.writeString(developersFilePath, GSON.toJson(developers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateNewMaxId(List<Developer> developers) {
        Developer developerWithMaxID = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(developerWithMaxID) ? developerWithMaxID.getId()+1 : 1l;
    }

    @Override
    public Developer getById(Long id) {
        return getAllDevelopers().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Developer add(Developer item) {
        List<Developer> currentDeveloper = getAllDevelopers();
        item.setId(generateNewMaxId(currentDeveloper));

        currentDeveloper.add(item);
        writeDevelopersToFile(currentDeveloper);

        return item;
    }

    @Override
    public void delete(Long id) {
        List<Developer> currentDeveloper = getAllDevelopers();
        currentDeveloper.removeIf(dev -> dev.getId().equals(id));

        writeDevelopersToFile(currentDeveloper);
    }

    @Override
    public Developer update(Developer updateDeveloper) {
        List<Developer> currentDeveloper = getAllDevelopers();

        currentDeveloper.forEach(dev -> {
            if(dev.getId().equals(updateDeveloper.getId())) {
                dev.setFirstName(updateDeveloper.getFirstName());
                dev.setLastName(updateDeveloper.getLastName());
                dev.setSpecialty(updateDeveloper.getSpecialty());
            }
        });

        writeDevelopersToFile(currentDeveloper);
        return updateDeveloper;
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevelopers();
    }
}
