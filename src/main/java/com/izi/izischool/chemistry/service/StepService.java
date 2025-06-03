package com.izi.izischool.chemistry.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.izi.izischool.chemistry.model.step.Step;
import com.izi.izischool.chemistry.common.util.TextUtils;
import com.izi.izischool.chemistry.model.step.Transition;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StepService {
    private final Map<String, Step> stepsMap = new HashMap<>();

    private String firstStepId;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/data/chemistry-module.json");
            List<Step> steps = mapper.readValue(is, new TypeReference<>() {});
            for (Step step : steps) {
                stepsMap.put(step.getId(), step);
            }
            setFirstStep("1");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки шагов", e);
        }
    }

    public Step getStepByIdOrByKey(String idOrKey) {
        Step step = stepsMap.get(idOrKey);
        if (step == null) {
            return stepsMap.values().stream()
                    .filter(s -> s.getKey() != null)
                    .filter(s -> idOrKey.equalsIgnoreCase(s.getKey().getName()))
                    .findFirst()
                    .orElse(null);
        }
        return step;
    }

    public List<Step> loadSteps(List<MultipartFile> files, boolean append, boolean link) {
        List<Step> steps = new ArrayList<>();
        if (files != null) {
            files.forEach(file -> {
                byte[] fileContent;
                try {
                    fileContent = file.getBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String sourceText = new String(fileContent, StandardCharsets.UTF_8);
                TextUtils.splitTextToNotEmptyTrimmedParts(sourceText, Step.IZI_DELIM_STEP)
                        .map(stepText -> new Step(stepText, true))
                        .forEach(steps::add);
            });
        }
        if (append) {
            for (Step step : steps) {
                stepsMap.put(step.getId(), step);
            }
        }
        if (link) {
            stepsMap.values().stream()
                    .filter(step -> step.getTransitions() != null)
                    .forEach(step -> {
                        for (Transition transition : step.getTransitions()) {
                            if (transition.getTargetStepKey() != null) {
                                stepsMap.values().stream()
                                        .filter(lookingStep -> lookingStep.getKey() != null)
                                        .filter(lookingStep -> transition.getTargetStepKey().equals(lookingStep.getKey().getName()))
                                        .findFirst()
                                        .ifPresent(lookingStep -> transition.setTargetStepId(lookingStep.getId()));
                            }
                        }
                    });
        }
        return steps;
    }

    public Step setFirstStep(String idOrKey) {
        Step step = getStepByIdOrByKey(idOrKey);
        if (step != null) {
            this.firstStepId = step.getId();
        }
        return step;
    }

    public String getFirstStepId() {
        return this.firstStepId;
    }

    public void clearSteps() {
        stepsMap.clear();
    }
}
