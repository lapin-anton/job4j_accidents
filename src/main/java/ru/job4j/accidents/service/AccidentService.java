package ru.job4j.accidents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident, String[] rulesIds) {
        setRules(accident, rulesIds);
        accidentMem.add(accident);
    }

    public void update(Accident accident, String[] rulesIds) {
        setRules(accident, rulesIds);
        accidentMem.update(accident);
    }

    public Accident findById(Integer id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> findAllTypes() {
        return accidentMem.findAllTypes();
    }

    public List<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }

    private Set<Rule> findAllByStrArray(String[] ids) {
        return accidentMem.findAllByStrArray(ids);
    }

    private void setRules(Accident accident, String[] rulesIds) {
        Set<Rule> rules = findAllByStrArray(rulesIds);
        accident.setRules(rules);
    }

}
