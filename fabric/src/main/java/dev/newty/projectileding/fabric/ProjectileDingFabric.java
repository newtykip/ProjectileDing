package dev.newty.projectileding.fabric;

import dev.newty.projectileding.ProjectileDing;
import net.fabricmc.api.ModInitializer;

public class ProjectileDingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ProjectileDing.init();
    }
}