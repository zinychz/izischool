package com.izi.izischool.chemistry.dto;

import com.izi.izischool.chemistry.model.content.Content;
import com.izi.izischool.chemistry.model.step.Key;
import com.izi.izischool.chemistry.model.step.Question;
import com.izi.izischool.chemistry.model.step.Transition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StepRequest {
    private Key key;
    private Content content;
    private Question question;
    private List<Transition> transitions;
}
