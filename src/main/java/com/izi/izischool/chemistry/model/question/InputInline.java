package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.model.LocalizedText;

public class InputInline extends QuestionInput {

    @Override
    public QuestionType getType() {
        return QuestionType.INPUT;
    }

    public InputInline() {
        super();
    }

    public InputInline(String id, LocalizedText text) {
        super();
    }

    public InputInline(String text) {
        this(text, false);
    }

    public InputInline(String text, boolean useIziDelims) {
        super(text, useIziDelims);
    }
}
