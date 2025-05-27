package com.izi.izischool.chemistry.model.content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageBlock extends ContentBlock {
    private String src;
    private String alt;
    private String width;

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.IMAGE;
    }
}

