package com.izi.izischool.chemistry.model.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageBlock extends ContentBlock {
    private String src;
    private String alt;
    private String width;

    public ImageBlock(String text) {
        this(text, false);
    }

    public ImageBlock(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            this.src = text;
        }
    }

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.IMAGE;
    }
}

