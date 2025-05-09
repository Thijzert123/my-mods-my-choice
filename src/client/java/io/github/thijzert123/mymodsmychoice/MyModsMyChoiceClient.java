package io.github.thijzert123.mymodsmychoice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thijzert123.mymodsmychoice.config.BooleanController;
import io.github.thijzert123.mymodsmychoice.config.Category;
import io.github.thijzert123.mymodsmychoice.config.LabelController;
import io.github.thijzert123.mymodsmychoice.config.ModSet;
import net.fabricmc.api.ClientModInitializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyModsMyChoiceClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
//        testModSets();
//        testCategory();
    }

    private void testModSets() {
        final Map<String, ModSet> modSets = new HashMap<>();
        modSets.put("sodium", new ModSet("Sodium", "faster!!!!!").addMod("sodium").addMod("indium"));

        final ObjectMapper objectMapper = new ObjectMapper();
        try { // TODO fix try/catch
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(MyModsMyChoice.MOD_SETS_CONFIG_PATH.toFile(), modSets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void testCategory() {
        final Category category = new Category(new Category.Text("MY new CaTeGoRy!!!", true, "green"), "good description")
                .addController(new LabelController("Label", "Description"))
                .addController(new BooleanController("This is a boolean controller", "descriptionh ihihi", "sodium"));

        final List<Category> listtowrite = new ArrayList<>();
        listtowrite.add(category);
        listtowrite.add(category);
        final ObjectMapper objectMapper = new ObjectMapper();
        final File file = MyModsMyChoice.CONFIG_SCREEN_CONFIG_PATH.toFile();
        try {
            //objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, listtowrite);
            final List<Category> categories = objectMapper.readValue(file, new TypeReference<>() {});
            MyModsMyChoice.LOGGER.info(categories.getFirst().controllers.getFirst().text);
        } catch (final IOException ioException){
            throw new RuntimeException(ioException);
        }
    }
}