package com.izi.izischool.chemistry.model.step;

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
public class QuestionInput {
    private String id;
    private LocalizedText text;

    public QuestionInput(String text) {
        this(text, false);
    }

    public QuestionInput(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            if (useIziDelims) {
                if (text.contains(Constants.Spliterator.IZI_ID)) {
                    TextUtils.splitTextToHeadAndTail(text, Constants.Spliterator.IZI_ID, null,
                            (head, tail) -> {
                                if (head != null) {
                                    this.text = new LocalizedText(head, true);
                                }
                                this.id = tail;
                            });
                } else {
                    this.text = new LocalizedText(text, true);
                }
            } else {
                this.text = new LocalizedText(text);
            }
        }
    }
}
