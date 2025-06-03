package com.izi.izischool.chemistry.model;

import com.izi.izischool.chemistry.common.util.TextUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class LocalizedText {
    public static final String IZI_DEFAULT_LOCALE = ":izi_default_locale:";
    public static final String IZI_DELIM_LOCALIZED = ":izi_localized:";
    public static final String IZI_DELIM_LANG = ":izi_lang:";

    private Map<String, String> localized = new HashMap<>(); // Example: EN -> "Some text"

    public LocalizedText(String text) {
        this(text, false);
    }

    public LocalizedText(String text, boolean useIziDelims) {
        if (useIziDelims && text != null) {
            if (text.contains(IZI_DELIM_LOCALIZED)) {
                TextUtils.splitTextToNotEmptyTrimmedParts(text, IZI_DELIM_LOCALIZED)
                        .forEach(this::addLocalizedText);
            } else {
                this.localized.put(IZI_DEFAULT_LOCALE, text);
            }
        } else {
            this.localized.put(IZI_DEFAULT_LOCALE, text);
        }
    }

    private void addLocalizedText(String text) {
        if (text.contains(IZI_DELIM_LANG)) {
            TextUtils.splitTextToHeadAndTail(text, IZI_DELIM_LANG, null,
                    (head, tail) -> {
                        if(head != null && tail != null) {
                            this.localized.put(tail, head);
                        } else if(tail == null && head != null) {
                            this.localized.put(IZI_DEFAULT_LOCALE, head);
                        }
                    });
        } else {
            this.localized.put(IZI_DEFAULT_LOCALE, text);
        }
    }
}
