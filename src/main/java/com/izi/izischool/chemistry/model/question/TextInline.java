package com.izi.izischool.chemistry.model.question;

import com.izi.izischool.chemistry.model.LocalizedText;

public class TextInline extends QuestionInput {

    @Override
    public QuestionType getType() {
        return QuestionType.TEXT;
    }

    public TextInline() {
        super();
    }

    public TextInline(String id, LocalizedText text) {
        super();
    }

    public TextInline(String text) {
        this(text, false);
    }

    public TextInline(String text, boolean useIziDelims) {
        super(text, useIziDelims);
    }
}
