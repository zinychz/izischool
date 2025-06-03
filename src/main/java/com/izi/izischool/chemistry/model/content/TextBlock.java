package com.izi.izischool.chemistry.model.content;

import com.izi.izischool.chemistry.model.LocalizedText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextBlock extends ContentBlock {
    private LocalizedText value;
    private TextFormat format = TextFormat.PLAIN; // plain, markdown, html

    public TextBlock(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            this.value = new LocalizedText(text, useIziDelims);
            this.format = TextFormat.PLAIN;
        }
    }

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.TEXT;
    }
}
