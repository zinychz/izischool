package com.izi.izischool.chemistry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerResponse {
    private boolean correct;
    private String explanation;
    private String nextStepId;
}
