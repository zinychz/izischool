package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.model.LocalizedText;

public class Text extends QuestionInput {

    @Override
    public QuestionType getType() {
        return QuestionType.TEXT;
    }

    public Text() {
        super();
    }

    public Text(String id, LocalizedText text) {
        super();
    }

    public Text(String text) {
        this(text, false);
    }

    public Text(String text, boolean useIziDelims) {
        super(text, useIziDelims);
    }
}
