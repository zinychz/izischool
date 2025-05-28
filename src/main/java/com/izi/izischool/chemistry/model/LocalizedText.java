package com.izi.izischool.chemistry.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class LocalizedText {
    public static final String INTERNAL_DEFAULT_LOCALE_KEY = ":izi_default_locale:";
    private Map<String, String> localized = new HashMap<>(); // Example: EN -> "Some text"

    public LocalizedText(String text) {
        localized.put(INTERNAL_DEFAULT_LOCALE_KEY, text);
    }
}
