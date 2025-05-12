package io.github.thijzert123.mymodsmychoice;

import io.github.thijzert123.mymodsmychoice.config.manager.DisabledModSets;
import io.github.thijzert123.mymodsmychoice.config.manager.ModSets;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.ModContainerImpl;
import settingdust.preloadingtricks.SetupModCallback;
import settingdust.preloadingtricks.fabric.FabricModSetupService;

import java.util.List;

public class PreLaunch implements SetupModCallback {
    private static final FabricModSetupService SERVICE = FabricModSetupService.INSTANCE;

    public PreLaunch() {
        MyModsMyChoice.LOGGER.info("I'm here before the rest!");

        final List<String> modsToDisable = ModSets.modSetsToMods(DisabledModSets.load());
        FabricLoader.getInstance().getAllMods().forEach((modContainer -> {
            final String modId = modContainer.getMetadata().getId();
            if (modsToDisable.contains(modId)) {
                MyModsMyChoice.LOGGER.info("Removing {}", modId);
                SERVICE.remove((ModContainerImpl) modContainer);
            }
        }));
    }
}
