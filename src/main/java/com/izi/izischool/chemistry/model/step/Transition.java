package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.common.util.TextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transition {
    public static final String IZI_DELIM_TARGET_KEY = ":izi_target_key:";
    public static final String IZI_DELIM_CONDITION = ":izi_condition:";

    private String targetStepKey;
    private String condition;   // Examples: "a=ice", "b=steam", "CHOICE=29"
    private String targetStepId;

    public Transition(String text) {
        this(text, false);
    }

    public Transition(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            if (useIziDelims && text.contains(IZI_DELIM_CONDITION)) {
                TextUtils.splitTextToHeadAndTail(text, IZI_DELIM_CONDITION, null,
                        (head, tail) -> {
                            this.targetStepKey = head;
                            this.condition = tail;
                        });
            } else {
                this.targetStepKey = text;
            }
        }
    }
}
