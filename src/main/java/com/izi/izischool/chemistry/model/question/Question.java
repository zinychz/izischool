package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.common.Constants;
import com.izi.izischool.chemistry.common.util.TextUtils;
import com.izi.izischool.chemistry.model.LocalizedText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    public static final String IZI_DELIM_EXPLANATION = ":izi_explanation:";
    public static final String IZI_DELIM_CORRECT_ANSWER = ":izi_correct_answer:";
    public static final String IZI_DELIM_QUESTION = ":izi_question:";

    private List<QuestionInput> inputs;
    private LocalizedText correctAnswer;
    private LocalizedText explanation;


    public Question(String text) {
        this(text, false);
    }

    public Question(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            this.inputs = new ArrayList<>();
            if (useIziDelims) {
                String headText = text;
                headText = fillExplanationOrAnswer(headText, IZI_DELIM_EXPLANATION,
                        tail -> this.explanation = new LocalizedText(tail, true));
                if (headText != null) {
                    headText = fillExplanationOrAnswer(headText, IZI_DELIM_CORRECT_ANSWER,
                            tail -> this.correctAnswer = new LocalizedText(tail, true));
                }
                if (headText != null) {
                    if (headText.contains(QuestionInput.IZI_DELIM_INPUT)) {
                        TextUtils.splitTextToNotEmptyTrimmedParts(text, QuestionInput.IZI_DELIM_INPUT)
                                .forEach(this::addQuestionPart);
                    } else {
                        addQuestionPart(headText);
                    }
                }
            } else {
                this.inputs.add(new Text(text, false));
            }
        }
    }

    private void addQuestionPart(String text) {
        if (text.contains(Constants.Spliterator.IZI_TYPE)) {
            TextUtils.splitTextToHeadAndTail(text, Constants.Spliterator.IZI_TYPE, null,
                    (head, tail) -> this.inputs.add(QuestionType.questionInputFrom(tail, head, true)));
        } else {
            this.inputs.add(new Text(text, true));
        }
    }

    private String fillExplanationOrAnswer(String text, String delim, Consumer<String> fillAction) {
        return TextUtils.splitTextToHeadAndTail(text, delim, tail -> {
            if(tail != null && !tail.isEmpty()) {
                fillAction.accept(tail);
            }
        });
    }
}
