package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooleanController extends LabelController {
    @JsonProperty("mod_set")
    public String modSetId;

    public BooleanController() {}

    public BooleanController(final String text, final String description, final String modSetId) {
        super(text, description);
        this.modSetId = modSetId;
    }
}
