package com.izi.izischool.chemistry.model.step;

import com.izi.izischool.chemistry.model.content.Content;
import com.izi.izischool.chemistry.common.util.TextUtils;
import com.izi.izischool.chemistry.model.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    public static final String IZI_DELIM_STEP = ":izi_step:";
    private static final String EQUAL_CONDITION = "=";


    private String id; // inner unique id, not null
    private Content content;
    private Question question;
    private List<Transition> transitions;
    private Key key; // custom key, nullable, it can be unique or not

    public Step(String text) {
        this(text, false);
    }

    public Step(String text, boolean useIziDelims) {
        if (text != null && !text.isEmpty()) {
            if (useIziDelims) {
                this.id = UUID.randomUUID().toString();
                String headText = text;
                headText = TextUtils.splitTextToHeadAndTail(headText, Key.IZI_DELIM_KEY, tail -> {
                    if(tail != null && !tail.isEmpty()) {
                        this.key = new Key(tail);
                    }
                });
                if (headText != null && headText.contains(Transition.IZI_DELIM_TARGET_KEY)) {
                    this.transitions = new ArrayList<>();
                    List<String> parts =
                            TextUtils.splitTextToNotEmptyTrimmedParts(headText, Transition.IZI_DELIM_TARGET_KEY).toList();
                    if (parts.size() > 1) {
                        headText = parts.get(0);
                        parts.stream().skip(1)
                                .forEach(transitionText -> this.transitions.add(new Transition(transitionText, true)));
                    } else if (parts.size() == 1) {
                        if (headText.startsWith(Transition.IZI_DELIM_TARGET_KEY)) {
                            headText = null;
                            this.transitions.add(new Transition(parts.get(0), true));
                        } else {
                            headText = parts.get(0);
                        }
                    } else {
                        headText = null;
                    }
                }
                if (headText != null) {
                    headText = TextUtils.splitTextToHeadAndTail(headText, Question.IZI_DELIM_QUESTION, tail -> {
                        if(tail != null && !tail.isEmpty()) {
                            this.question = new Question(tail, true);
                        }
                    });
                }
                if (headText != null) {
                    this.content = new Content(headText, true);
                }
            } else {
                this.content = new Content(text);
            }
        }
    }

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
        // examples: "a=ice", "CHOICE=29"
        String[] parts = condition.split(EQUAL_CONDITION);
        if (parts.length != 2) {
            return false;
        }

        String key = parts[0].trim();
        String value = parts[1].trim();

        return value.equals(answers.get(key));
    }
}
