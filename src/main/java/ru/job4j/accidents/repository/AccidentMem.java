package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Accident> store = new ConcurrentHashMap<>();
    private Map<Integer, AccidentType> types = new HashMap<>();
    private Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и велосипед"));
        rules.put(1, new Rule(1, "Статья 1"));
        rules.put(2, new Rule(2, "Статья 2"));
        rules.put(3, new Rule(3, "Статья 3"));
        Set<Rule> rules1 = Set.of(rules.get(1));
        Set<Rule> rules2 = Set.of(rules.get(2), rules.get(3));
        Set<Rule> rules3 = Set.of(rules.get(1), rules.get(2));
        add(new Accident(0, "Иван Иванов", types.get(1), "Москва, Варшавское ш., 10",
                "Неправильная парковка", rules1));
        add(new Accident(0, "Петр Петров", types.get(2), "С-Петербург, Невский пр-т, 143", "ДТП",
                rules2));
        add(new Accident(0, "Сергей Сергеев", types.get(3), "Краснодар, ул. Калинина, 55",
                "Проезд на красный сигнал светофора", rules3));
    }

    public Accident add(Accident accident) {
        accident.setId(currentId.incrementAndGet());
        accident.setType(getTypeById(accident.getType().getId()));
        store.put(accident.getId(), accident);
        return accident;
    }

    public Accident update(Accident accident) {
        accident.setType(getTypeById(accident.getType().getId()));
        store.replace(accident.getId(), accident);
        return accident;
    }

    public List<Accident> findAll() {
        return new ArrayList<>(store.values());
    }

    public Accident findById(Integer id) {
        return store.get(id);
    }

    public List<AccidentType> findAllTypes() {
        return new ArrayList<>(types.values());
    }

    private AccidentType getTypeById(int id) {
        return types.get(id);
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(rules.values());
    }

    public Set<Rule> findAllByStrArray(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id: ids) {
            rsl.add(rules.get(Integer.parseInt(id)));
        }
        return rsl;
    }
}
