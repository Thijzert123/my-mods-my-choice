package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Base controller for all controllers
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "controller_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LabelController.class, name = "label"),
        @JsonSubTypes.Type(value = BooleanController.class, name = "boolean"),
        @JsonSubTypes.Type(value = CyclingController.class, name = "cycling"),
        @JsonSubTypes.Type(value = ModSetGroupController.class, name = "mod_set_group"),
        @JsonSubTypes.Type(value = ControllerGroupController.class, name = "controller_group")
})
public class LabelController {
    public String text;
    public String description;

    public LabelController() {}

    public LabelController(final String text, final String description) {
        this.text = text;
        this.description = description;
    }
}
