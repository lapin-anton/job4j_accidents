package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Accident> store = new ConcurrentHashMap<>();
    private List<AccidentType> types = new ArrayList<>();

    public AccidentMem() {
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        add(new Accident(0, "Иван Иванов", types.get(0), "Москва, Варшавское ш., 10", "Неправильная парковка"));
        add(new Accident(0, "Петр Петров", types.get(1), "С-Петербург, Невский пр-т, 143", "ДТП"));
        add(new Accident(0, "Сергей Сергеев", types.get(2), "Краснодар, ул. Калинина, 55",
                "Проезд на красный сигнал светофора"));
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
        return types;
    }

    private AccidentType getTypeById(int id) {
        return types.stream()
                .filter(e -> e.getId() == id).findFirst()
                .orElse(null);
    }
}
