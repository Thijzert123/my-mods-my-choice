package io.github.thijzert123.mymodsmychoice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import io.github.thijzert123.mymodsmychoice.MyModsMyChoice;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// What a normal user would change (enabling/disabling mods)
public class InGameConfig implements ModMenuApi {
    private YetAnotherConfigLib generateConfig() {
        YetAnotherConfigLib.Builder configBuilder;

        try {
            configBuilder = YetAnotherConfigLib.createBuilder()
                    .title(Text.of("My Mods, My Choice"))
                    .categories(loadConfigCategories());
        } catch (final IOException ioException) {
            final String message = ioException.getMessage();
            MyModsMyChoice.LOGGER.error(message, ioException);
            configBuilder = generateErrorConfigBuilder(message);
        }

        return configBuilder.build();
    }

    private List<ConfigCategory> loadConfigCategories() throws IOException {
        final List<ConfigCategory> configCategories = new ArrayList<>();
        final List<Category> modSetCategories = deserializeConfigFile();

        for (final Category modSetCategory : modSetCategories) {
            final ConfigCategory.Builder categoryBuilderToAdd = ConfigCategory.createBuilder()
                    .name(Text.of(modSetCategory.text.text))
                    .tooltip(Text.of(modSetCategory.description))
                    .options(loadConfigOptions(modSetCategory.controllers));

            configCategories.add(categoryBuilderToAdd.build());
        }

        return configCategories;
    }

    private List<Option<?>> loadConfigOptions(final List<LabelController> modSetControllers) {
        final List<Option<?>> configOptions = new ArrayList<>();

        for (final LabelController modSetController : modSetControllers) {
            final Option.Builder<?> optionBuilder = Option.createBuilder()
                    .name(Text.of(modSetController.text))
                    .description(OptionDescription.of(Text.of(modSetController.description)));

            if (modSetController instanceof ControllerGroupController) {

            } else if (modSetController instanceof ModSetGroupController) {

            } else if (modSetController instanceof CyclingController) {

            } else if (modSetController instanceof BooleanController) {
                configOptions.add(getBooleanOption((BooleanController) modSetController));
            } else {
                final LabelOption labelOption = LabelOption.createBuilder()
                                .line(Text.of(modSetController.text))
                                        .line(Text.of(modSetController.description))
                                                .build();
                configOptions.add(labelOption);
                continue;
            }

            configOptions.add(optionBuilder.build());
        }

        return configOptions;
    }

    private Option<Boolean> getBooleanOption(final BooleanController booleanController) {
        return Option.<Boolean>createBuilder()
                .name(Text.of(booleanController.text))
                .description(OptionDescription.of(Text.of(booleanController.description)))
                .controller(opt -> BooleanControllerBuilder.create(opt)
                        .coloured(true))
                .build();
    }

    private List<Category> deserializeConfigFile() throws IOException {
        return new ObjectMapper().readValue(MyModsMyChoice.CONFIG_SCREEN_CONFIG_PATH.toFile(),
                new TypeReference<>() {});
    }

    private YetAnotherConfigLib.Builder generateErrorConfigBuilder(final String message) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("My Mods, My Choice"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("userconfig.category.error.name"))
                        .option(LabelOption
                                .create(Text.translatable("userconfig.category.error.option.name")))
                        .option(LabelOption
                                .create(Text.of(message)))
                        .build());
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> generateConfig().generateScreen(parentScreen);
    }
}
