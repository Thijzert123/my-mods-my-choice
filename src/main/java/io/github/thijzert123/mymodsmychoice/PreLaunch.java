package io.github.thijzert123.mymodsmychoice;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.ModContainerImpl;
import settingdust.preloadingtricks.SetupModCallback;
import settingdust.preloadingtricks.fabric.FabricModSetupService;

import java.util.List;

public class PreLaunch implements SetupModCallback {
    private static final FabricModSetupService SERVICE = FabricModSetupService.INSTANCE;
    private final FabricLoader fabricLoader;

    public PreLaunch() {
        MyModsMyChoice.LOGGER.info("I'm here before the rest!");

        fabricLoader = FabricLoader.getInstance();

        final List<String> disabledMods = DisabledMods.load();
        fabricLoader.getAllMods().forEach((modContainer -> {
            final String modId = modContainer.getMetadata().getId();
            if (disabledMods.contains(modId)) {
                MyModsMyChoice.LOGGER.info("Removing " + modId);
                SERVICE.remove((ModContainerImpl) modContainer);
            }
        }));
    }
}
