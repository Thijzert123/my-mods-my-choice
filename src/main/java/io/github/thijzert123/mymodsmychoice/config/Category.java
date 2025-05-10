package io.github.thijzert123.mymodsmychoice.config;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public static class Text {
        public String text;
        public boolean bold;
        public String color;

        public Text() {}

        public Text(final String text, final boolean bold, final String color) {
            this.text = text;
            this.bold = bold;
            this.color = color;
        }
    }

    public Text text;
    public String description;
    public List<LabelController> controllers = new ArrayList<>();

    public Category() {}

    public Category(final Text text, final String description) {
        this.text = text;
        this.description = description;
    }

    // Used for Jackson deserialization
    public Category(final Text text, final String description, final List<LabelController> controllers) {
        this.text = text;
        this.description = description;
        this.controllers = controllers;
    }

    public Category addController(final LabelController controller) {
        controllers.add(controller);
        return this;
    }
}
