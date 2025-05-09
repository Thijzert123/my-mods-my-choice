package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ModSetGroupController extends LabelController implements CollapsableController {
    @JsonProperty("mod_sets")
    public List<String> modSetIds = new ArrayList<>();

    public ModSetGroupController() {}

    public ModSetGroupController(final String text, final String description) {
        super(text, description);
    }

    public ModSetGroupController addModSet(final String modSetId) {
        modSetIds.add(modSetId);
        return this;
    }
}
