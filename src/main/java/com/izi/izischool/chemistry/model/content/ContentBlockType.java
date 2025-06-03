package com.izi.izischool.chemistry.model.content;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ContentBlockType {
    TEXT(TextBlock::new),
    IMAGE(ImageBlock::new);

    private final BiFunction<String, Boolean, ContentBlock> contentBlockCreator;

    ContentBlockType(BiFunction<String, Boolean, ContentBlock> contentBlockCreator) {
        this.contentBlockCreator = contentBlockCreator;
    }

    public static ContentBlock contentBlockFrom(String type, String text, boolean useIziDelims) {
        return Arrays.stream(values())
                .filter(blockType -> blockType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(TEXT)
                .contentBlockCreator.apply(text, useIziDelims);
    }
}
