package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.common.util.TextUtils;
import com.izi.izischool.chemistry.model.LocalizedText;
import com.izi.izischool.chemistry.model.content.Content;
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
    public static final String IZI_DELIM_QUESTION = ":izi_question:";
    public static final String IZI_DELIM_EXPLANATION = ":izi_explanation:";
    public static final String IZI_DELIM_CORRECT_ANSWER = ":izi_correct_answer:";
    public static final String IZI_DELIM_CHOICE = ":izi_choice:";
    public static final String IZI_DELIM_INPUT = ":izi_input:";

    private QuestionType type = QuestionType.INFO_ONLY;
    private Content content;

    private List<QuestionInput> inputs; // for TEXT_INPUT only
    private List<ChoiceOption> options; // for CHOICE only
    private LocalizedText correctAnswer;
    private LocalizedText explanation;

    public Question(String text) {
        this(text, false);
    }

    public Question(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            if (useIziDelims) {
                String headText = text;
                headText = fillExplanationOrAnswer(headText, IZI_DELIM_EXPLANATION,
                        tail -> this.explanation = new LocalizedText(tail, true));
                if (headText != null) {
                    headText = fillExplanationOrAnswer(headText, IZI_DELIM_CORRECT_ANSWER,
                            tail -> this.correctAnswer = new LocalizedText(tail, true));
                }
                if (headText != null) {
                    if (headText.contains(IZI_DELIM_CHOICE)) {
                        this.options = new ArrayList<>();
                        this.type = QuestionType.CHOICE;
                        headText = fillOptionsOrInputs(headText, IZI_DELIM_CHOICE,
                                choiceText -> this.options.add(new ChoiceOption(choiceText, true)));
                    } else if (headText.contains(IZI_DELIM_INPUT)) {
                        this.inputs = new ArrayList<>();
                        this.type = QuestionType.TEXT_INPUT;
                        headText = fillOptionsOrInputs(headText, IZI_DELIM_INPUT,
                                inputText -> this.inputs.add(new QuestionInput(inputText, true)));
                    }
                }
                if (headText != null) {
                    this.content = new Content(headText, true);
                }
            } else {
                this.content = new Content(text);
            }
        }
    }

    private String fillExplanationOrAnswer(String text, String delim, Consumer<String> fillAction) {
        return TextUtils.splitTextToHeadAndTail(text, delim, tail -> {
            if(tail != null && !tail.isEmpty()) {
                fillAction.accept(tail);
            }
        });
    }

    private String fillOptionsOrInputs(String text, String delim, Consumer<String> fillAction) {
        List<String> parts =
                TextUtils.splitTextToNotEmptyTrimmedParts(text, delim).toList();
        String headText = null;
        if (parts.size() > 1) {
            if (text.startsWith(delim)) {
                parts.forEach(fillAction);
            } else {
                headText = parts.get(0);
                parts.stream()
                        .skip(1)
                        .forEach(fillAction);
            }
        } else if (parts.size() == 1) {
            if (text.startsWith(delim)) {
                fillAction.accept(parts.get(0));
            } else {
                headText = parts.get(0);
                fillAction.accept(null);
            }
        }
        return headText;
    }
}
