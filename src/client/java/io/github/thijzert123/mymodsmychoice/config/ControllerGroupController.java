package io.github.thijzert123.mymodsmychoice.config;

import java.util.ArrayList;
import java.util.List;

public class ControllerGroupController extends LabelController implements CollapsableController {
    public List<LabelController> controllers = new ArrayList<>();

    public ControllerGroupController(final String text, final String description) {
        super(text, description);
    }

    public ControllerGroupController addController(final LabelController controller) {
        controllers.add(controller);
        return this;
    }
}
