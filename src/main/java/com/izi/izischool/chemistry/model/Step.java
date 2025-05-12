package com.izi.izischool.chemistry.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Step {
    private String id;
    private String type;
    private Content content;
    private List<Option> options;
    private String correctAnswer;
    private String explanation;
    private String nextStepId;
}
