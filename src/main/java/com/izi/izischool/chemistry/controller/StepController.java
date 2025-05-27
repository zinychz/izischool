package com.izi.izischool.chemistry.controller;

import com.izi.izischool.chemistry.dto.AnswerRequest;
import com.izi.izischool.chemistry.dto.AnswerResponse;
import com.izi.izischool.chemistry.model.step.Step;
import com.izi.izischool.chemistry.model.step.QuestionType;
import com.izi.izischool.chemistry.service.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

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

        boolean correct = true;
        if (step.getQuestion() != null && step.getQuestion().getCorrectAnswer() != null &&
                (QuestionType.CHOICE == step.getQuestion().getType() || QuestionType.TEXT_INPUT == step.getQuestion().getType())) {
            correct = step.getQuestion().getCorrectAnswer().equalsIgnoreCase(request.getAnswer().trim());
        }

        Map<String, String> answers = step.getQuestion() != null && QuestionType.CHOICE == step.getQuestion().getType() ?
                Map.of(QuestionType.CHOICE.name(), request.getAnswer()) : Map.of(request.getAnswer(), request.getAnswer());

        return new AnswerResponse(correct, step.getQuestion() == null ? null : step.getQuestion().getExplanation(),
                step.getNextStepId(answers));
    }
}
