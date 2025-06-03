package com.izi.izischool.chemistry.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TextUtils {

    public static Stream<String> splitTextToNotEmptyTrimmedParts(String text, String delim) {
        if (text == null) {
            return Stream.empty();
        }
        return Arrays.stream(text.split(delim))
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(stepText -> !stepText.isEmpty());
    }

    public static String splitTextToHeadAndTail(String text, String iziDelim, Consumer<String> tailConsumer,
                                                BiConsumer<String, String> headTailConsumer) {
        String head = text;
        String tail = null;
        if (head != null && head.contains(iziDelim)) {
            List<String> parts = splitTextToNotEmptyTrimmedParts(head, iziDelim).toList();
            if (!parts.isEmpty()) {
                if (parts.size() == 1) {
                    if (head.endsWith(iziDelim)) {
                        head = parts.get(0);
                    } else {
                        head = null;
                        tail = parts.get(0);
                    }
                } else {
                    tail = parts.get(parts.size() - 1);
                    StringBuilder parsedTextBuilder = new StringBuilder();
                    for (int i = 0; i < parts.size() - 1; i++) {
                        parsedTextBuilder.append(parts.get(i));
                    }
                    head = parsedTextBuilder.toString();
                }
            } else {
                head = null;
            }
        }
        if (tailConsumer != null) {
            tailConsumer.accept(tail);
        }
        if (headTailConsumer != null) {
            headTailConsumer.accept(head, tail);
        }
        return head;
    }

    public static String splitTextToHeadAndTail(String text, String iziDelim, Consumer<String> tailConsumer) {
        return splitTextToHeadAndTail(text, iziDelim, tailConsumer, null);
    }
}
