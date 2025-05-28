package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.model.LocalizedText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionInput {
    private String id;
    private LocalizedText text;
}
