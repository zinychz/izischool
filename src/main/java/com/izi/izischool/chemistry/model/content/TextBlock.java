package com.izi.izischool.chemistry.model.content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextBlock extends ContentBlock {
    private String value;
    private TextFormat format; // plain, markdown, html

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.TEXT;
    }
}
