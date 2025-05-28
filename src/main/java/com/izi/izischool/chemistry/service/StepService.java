package com.izi.izischool.chemistry.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.izi.izischool.chemistry.model.step.Step;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StepService {
    private final Map<String, Step> stepsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/data/chemistry-module.json");
            List<Step> steps = mapper.readValue(is, new TypeReference<>() {});
            for (Step step : steps) {
                stepsMap.put(step.getId(), step);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки шагов", e);
        }
    }

    public Step getStepById(String id) {
        return stepsMap.get(id);
    }
}
