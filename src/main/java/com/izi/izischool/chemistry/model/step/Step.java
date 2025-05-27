package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.model.content.Content;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Step {

    private static final String EQUAL_CONDITION = "=";

    private String id; // inner unique id, not null
    private Key key; // custom key, nullable, it can be unique or not
    private Content content;
    private Question question;

    private List<Transition> transitions;

    public String getNextStepId(Map<String, String> answers) {
        if (transitions == null || transitions.isEmpty()) {
            return null;
        }

        for (Transition transition : transitions) {
            if (transition.getCondition() == null) {
                return transition.getTargetStepId(); // unconditional transition
            }
            if (evaluateCondition(transition.getCondition(), answers)) {
                return transition.getTargetStepId();
            }
        }

        return null;
    }

    private boolean evaluateCondition(String condition, Map<String, String> answers) {
        // examples: "a=лед", "CHOICE=29"
        String[] parts = condition.split(EQUAL_CONDITION);
        if (parts.length != 2) {
            return false;
        }

        String key = parts[0].trim();
        String value = parts[1].trim();

        return value.equals(answers.get(key));
    }
}
