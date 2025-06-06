package com.izi.izischool.chemistry.model.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.izi.izischool.chemistry.common.Constants;
import com.izi.izischool.chemistry.common.util.TextUtils;
import com.izi.izischool.chemistry.model.LocalizedText;
import com.izi.izischool.chemistry.model.content.TextFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Text.class, name = "TEXT"),
        @JsonSubTypes.Type(value = TextInline.class, name = "TEXT_INLINE"),
        @JsonSubTypes.Type(value = Input.class, name = "INPUT"),
        @JsonSubTypes.Type(value = InputInline.class, name = "INPUT_INLINE"),
        @JsonSubTypes.Type(value = Choice.class, name = "CHOICE")
})
public abstract class QuestionInput {

    public static final String IZI_DELIM_INPUT = ":izi_input:";

    private String id;
    private LocalizedText text;
    private TextFormat format = TextFormat.PLAIN;

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
                                    if (head.contains(Constants.Spliterator.IZI_FORMAT)) {
                                        TextUtils.splitTextToHeadAndTail(head, Constants.Spliterator.IZI_FORMAT, null,
                                                (headText, tailFormat) -> {
                                                    if (headText != null) {
                                                        this.setText(new LocalizedText(headText, true));
                                                    }
                                                    this.setFormat(TextFormat.fromString(tailFormat));
                                                });
                                    } else {
                                        this.setText(new LocalizedText(head, true));
                                    }
                                }
                                this.setId(tail);
                            });
                } else {
                    this.setText(new LocalizedText(text, true));
                }
            } else {
                this.setText(new LocalizedText(text));
            }
        }
    }

    @JsonIgnore
    public abstract QuestionType getType(); // INFO_ONLY, TEXT_INPUT, CHOICE
}
