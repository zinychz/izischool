package com.izi.izischool.chemistry.model.question;


import java.util.Arrays;
import java.util.function.BiFunction;

public enum QuestionType {
    TEXT(Text::new),
    TEXT_INLINE(TextInline::new),
    INPUT(Input::new),
    INPUT_INLINE(InputInline::new),
    CHOICE(Choice::new);

    private final BiFunction<String, Boolean, QuestionInput> questionInputCreator;

    QuestionType(BiFunction<String, Boolean, QuestionInput> questionInputCreator) {
        this.questionInputCreator = questionInputCreator;
    }

    public static QuestionInput questionInputFrom(String type, String text, boolean useIziDelims) {
        return Arrays.stream(values())
                .filter(questionType -> questionType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(TEXT)
                .questionInputCreator.apply(text, useIziDelims);
    }
}
