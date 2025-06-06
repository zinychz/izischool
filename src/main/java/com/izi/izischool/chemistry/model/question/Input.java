package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.model.LocalizedText;

public class Input extends QuestionInput {

    @Override
    public QuestionType getType() {
        return QuestionType.INPUT;
    }

    public Input() {
        super();
    }

    public Input(String id, LocalizedText text) {
        super();
    }

    public Input(String text) {
        this(text, false);
    }

    public Input(String text, boolean useIziDelims) {
        super(text, useIziDelims);
    }
}
