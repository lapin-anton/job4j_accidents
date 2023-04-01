package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Accident> store = new ConcurrentHashMap<>();

    public AccidentMem() {
        add(new Accident(0, "Иван Иванов", "Москва, Варшавское ш., 10", "Неправильная парковка"));
        add(new Accident(0, "Петр Петров", "С-Петербург, Невский пр-т, 143", "ДТП"));
        add(new Accident(0, "Сергей Сергеев", "Краснодар, ул. Калинина, 55",
                "Проезд на красный сигнал светофора"));
    }

    public Accident add(Accident accident) {
        accident.setId(currentId.incrementAndGet());
        store.put(accident.getId(), accident);
        return accident;
    }

    public Accident update(Accident accident) {
        store.put(accident.getId(), accident);
        return accident;
    }

    public List<Accident> findAll() {
        return new ArrayList<>(store.values());
    }

    public Accident findById(Integer id) {
        return store.get(id);
    }
}
