package com.izi.izischool.chemistry.controller;

import com.izi.izischool.chemistry.dto.AnswerRequest;
import com.izi.izischool.chemistry.dto.AnswerResponse;
import com.izi.izischool.chemistry.model.Step;
import com.izi.izischool.chemistry.service.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/steps")
public class StepController {
    private final StepService stepService;

    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping("/{id}")
    public Step getStep(@PathVariable String id) {
        return stepService.getStepById(id);
    }

    @PostMapping("/{id}/check")
    public AnswerResponse checkAnswer(@PathVariable String id, @RequestBody AnswerRequest request) {
        Step step = stepService.getStepById(id);
        if (step == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Шаг не найден");
        }

        boolean correct = false;

        if (step.getType().equals("true_false") || step.getType().equals("multiple_choice") || step.getType().equals("text_input")) {
            correct = step.getCorrectAnswer().equalsIgnoreCase(request.getAnswer().trim());
        }

        return new AnswerResponse(correct, step.getExplanation(), step.getNextStepId());
    }
}
