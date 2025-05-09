package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CyclingController extends LabelController {
    @JsonProperty("mod_sets")
    public final List<String> modSetIds = new ArrayList<>();

    public CyclingController() {}

    public CyclingController(final String text, final String description) {
        super(text, description);
    }

    public CyclingController addModSetId(final String modSetId) {
        modSetIds.add(modSetId);
        return this;
    }
}
