package com.izi.izischool.chemistry.model.content;

import java.util.Arrays;

public enum TextFormat {
    PLAIN,
    MARKDOWN,
    HTML;

    public static TextFormat fromString(String formatString) {
        return fromString(formatString, PLAIN);
    }

    public static TextFormat fromString(String formatString, TextFormat defaultFormat) {
        return Arrays.stream(values())
                .filter(format -> format.name().equalsIgnoreCase(formatString))
                .findFirst()
                .orElse(defaultFormat);
    }
}
