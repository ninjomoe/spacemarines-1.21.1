package com.bossa.spacemarinearmor;

import com.bossa.spacemarinearmor.common.registry.PWCreativeTabs;
import com.bossa.spacemarinearmor.common.registry.PWItems;
import com.bossa.spacemarinearmor.common.registry.PWNetwork;
import com.bossa.spacemarinearmor.common.registry.PWRecipeSerializer;
import com.bossa.spacemarinearmor.server.data.tags.PWItemTagsProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod("space_marines")
public class SpaceMarines {
    public static final String MODID = "space_marines";

    public SpaceMarines(IEventBus modEventBus) {
        PWItems.ITEMS.register(modEventBus);
        PWItems.ARMOR_MATERIALS.register(modEventBus);
        PWCreativeTabs.TABS.register(modEventBus);
        PWRecipeSerializer.RECIPE_SERIALIZERS.register(modEventBus);
        modEventBus.addListener(PWNetwork::registerPayloads);
        modEventBus.addListener(this::dataSetup);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        boolean includeServer = event.includeServer();
        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(packOutput, event.getLookupProvider(), "space_marines", existingFileHelper) {
            @Override
            protected void addTags(HolderLookup.Provider provider) {
            }
        };
        generator.addProvider(includeServer, blockTagsProvider);
        generator.addProvider(includeServer, new PWItemTagsProvider(packOutput, event.getLookupProvider(), blockTagsProvider.contentsGetter(), existingFileHelper));
    }
}
