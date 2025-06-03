package com.izi.izischool.chemistry.model.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Key {

    public static final String IZI_DELIM_KEY = ":izi_key:";
    private String name;
    private boolean unique;

    public Key(String text) {
        if (text != null && !text.isEmpty()) {
            this.name = text;
            this.unique = true;
        }
    }
}
