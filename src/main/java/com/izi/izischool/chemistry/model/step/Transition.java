package com.izi.izischool.chemistry.model.step;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transition {
    private String condition;   // Examples: "a=лед", "b=пар", "CHOICE=29"
    private String targetStepId;
}
