package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.model.LocalizedText;
import com.izi.izischool.chemistry.model.content.Content;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Question {
    private QuestionType type;
    private Content content;

    private List<QuestionInput> inputs; // for TEXT_INPUT only
    private List<ChoiceOption> options; // for CHOICE only
    private LocalizedText correctAnswer;
    private LocalizedText explanation;
}
