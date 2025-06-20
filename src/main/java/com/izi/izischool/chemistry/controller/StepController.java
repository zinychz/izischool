package com.izi.izischool.chemistry.controller;

import com.izi.izischool.chemistry.dto.AnswerRequest;
import com.izi.izischool.chemistry.dto.AnswerResponse;
import com.izi.izischool.chemistry.dto.StepRequest;
import com.izi.izischool.chemistry.mapper.StepMapper;
import com.izi.izischool.chemistry.model.step.Step;
import com.izi.izischool.chemistry.model.question.QuestionType;
import com.izi.izischool.chemistry.service.StepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/steps")
public class StepController {
    private final StepService stepService;
    private final StepMapper stepMapper;

    public StepController(StepService stepService, StepMapper stepMapper) {
        this.stepService = stepService;
        this.stepMapper = stepMapper;
    }

    @PostMapping("/load")
    public ResponseEntity<List<Step>> loadSteps(@RequestParam(defaultValue = "false") boolean append,
                                                @RequestParam(defaultValue = "false") boolean link,
                                                @RequestPart(required = false) List<MultipartFile> files) {
        return ResponseEntity.ok(stepService.loadSteps(files, append, link));
    }

    @PostMapping("/first")
    public ResponseEntity<Step> setFirstStep(@RequestParam(defaultValue = "1", name = "id") String firstStepIdOrKey) {
        return ResponseEntity.ok(stepService.setFirstStep(firstStepIdOrKey));
    }

    @GetMapping("/first")
    public ResponseEntity<String> getFirstStepId() {
        return ResponseEntity.ok(stepService.getFirstStepId());
    }

    @DeleteMapping
    public ResponseEntity<Void> clearSteps() {
        stepService.clearSteps();
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Step> createStep(@RequestBody StepRequest stepRequestDto) {
        Step step = stepMapper.toStep(stepRequestDto);
        return ResponseEntity.ok(step);
    }

    @GetMapping("/{id}")
    public Step getStepByIdOrByKey(@PathVariable String id) {
        return stepService.getStepByIdOrByKey(id);
    }

    @PostMapping("/{id}/check")
    public AnswerResponse checkAnswer(@PathVariable String id, @RequestBody AnswerRequest request) {
        return new AnswerResponse(true, null, null);
//        Step step = stepService.getStepByIdOrByKey(id);
//        if (step == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Шаг не найден");
//        }
//
//        boolean correct = true;
//        if (step.getQuestion() != null && step.getQuestion().getCorrectAnswer() != null
//                && !step.getQuestion().getCorrectAnswer().getLocalized().isEmpty() &&
//                (QuestionType.CHOICE == step.getQuestion().getType() || QuestionType.INPUT == step.getQuestion().getType())) {
//            correct = step.getQuestion().getCorrectAnswer().getLocalized().entrySet().iterator().next().getValue()
//                    .equalsIgnoreCase(request.getAnswer().trim());
//        }
//
//        Map<String, String> answers = step.getQuestion() != null && QuestionType.CHOICE == step.getQuestion().getType() ?
//                Map.of(QuestionType.CHOICE.name(), request.getAnswer()) : Map.of(request.getAnswer(), request.getAnswer());
//
//        return new AnswerResponse(correct, step.getQuestion() == null ? null :
//                step.getQuestion().getExplanation() == null ? null :
//                        step.getQuestion().getExplanation().getLocalized().isEmpty() ? null :
//                                step.getQuestion().getExplanation().getLocalized().entrySet().iterator().next().getValue(),
//                step.getNextStepId(answers));
    }
}
