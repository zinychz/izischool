package com.izi.izischool.chemistry.model.content;

import com.izi.izischool.chemistry.common.Constants;
import com.izi.izischool.chemistry.common.util.TextUtils;
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
    private TextFormat format = TextFormat.MARKDOWN; // plain, markdown, html

    public TextBlock(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            if (text.contains(Constants.Spliterator.IZI_FORMAT)) {
                TextUtils.splitTextToHeadAndTail(text, Constants.Spliterator.IZI_FORMAT, null,
                        (head, tail) -> {
                            this.value = new LocalizedText(head, useIziDelims);
                            this.format = TextFormat.fromString(tail, TextFormat.MARKDOWN);
                        });
            } else {
                this.value = new LocalizedText(text, useIziDelims);
                this.format = TextFormat.MARKDOWN;
            }
        }
    }

    @Override
    public ContentBlockType getType() {
        return ContentBlockType.TEXT;
    }
}
