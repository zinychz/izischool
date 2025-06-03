package com.izi.izischool.chemistry.model.content;

import com.izi.izischool.chemistry.common.Constants;
import com.izi.izischool.chemistry.common.util.TextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private List<ContentBlock> blocks;

    public Content(String text) {
        this(text, false);
    }

    public Content(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            this.blocks = new ArrayList<>();
            if (useIziDelims) {
                if (text.contains(ContentBlock.IZI_DELIM_BLOCK)) {
                    TextUtils.splitTextToNotEmptyTrimmedParts(text, ContentBlock.IZI_DELIM_BLOCK)
                            .forEach(this::addTextAsBlock);
                } else {
                    addTextAsBlock(text);
                }
            } else {
                this.blocks.add(new TextBlock(text, false));
            }
        }
    }

    private void addTextAsBlock(String text) {
        if (text.contains(Constants.Spliterator.IZI_TYPE)) {
            TextUtils.splitTextToHeadAndTail(text, Constants.Spliterator.IZI_TYPE, null,
                    (head, tail) -> this.blocks.add(ContentBlockType.contentBlockFrom(tail, head, true)));
        } else {
            this.blocks.add(new TextBlock(text, true));
        }
    }
}
