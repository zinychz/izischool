package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.model.LocalizedText;

public class Choice extends QuestionInput {

    @Override
    public QuestionType getType() {
        return QuestionType.CHOICE;
    }

    public Choice() {
        super();
    }

    public Choice(String id, LocalizedText text) {
        super();
    }

    public Choice(String text) {
        this(text, false);
    }

    public Choice(String text, boolean useIziDelims) {
        super(text, useIziDelims);
    }
}
