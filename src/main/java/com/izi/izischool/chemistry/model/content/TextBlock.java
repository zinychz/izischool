package com.izi.izischool.chemistry.model.content;

import com.izi.izischool.chemistry.model.LocalizedText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextBlock extends ContentBlock {
    private LocalizedText value;
    private TextFormat format; // plain, markdown, html

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.TEXT;
    }
}
