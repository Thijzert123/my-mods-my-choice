package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooleanController extends LabelController {
    @JsonProperty("mod_set")
    public String modSetId;

    @JsonProperty("default_value")
    public boolean defaultValue;

    public BooleanController() {}
}
