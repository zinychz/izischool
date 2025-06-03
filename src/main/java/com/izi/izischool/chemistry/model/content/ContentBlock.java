package com.izi.izischool.chemistry.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextBlock.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ImageBlock.class, name = "IMAGE")
})
public abstract class ContentBlock {
    public static final String IZI_DELIM_BLOCK = ":izi_block:";
    @JsonIgnore
    public abstract ContentBlockType getType(); // text, image, etc.
}
