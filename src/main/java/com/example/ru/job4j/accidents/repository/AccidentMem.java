package com.example.ru.job4j.accidents.repository;

import com.example.ru.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;

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
        return store.get(accident.getId());
    }

    public Map<Integer, Accident> findAll() {
        return store;
    }

}
