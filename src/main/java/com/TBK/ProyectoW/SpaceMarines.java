//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW;

import com.TBK.ProyectoW.common.registry.PWCreativeTabs;
import com.TBK.ProyectoW.common.registry.PWItems;
import com.TBK.ProyectoW.common.registry.PWRecipeSerializer;
import com.TBK.ProyectoW.server.data.tags.PWItemTagsProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("space_marines")
public class SpaceMarines {
    public static final String MODID = "space_marines";

    public SpaceMarines() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        PWItems.ITEMS.register(modEventBus);
        PWCreativeTabs.TABS.register(modEventBus);
        PWRecipeSerializer.RECIPE_SERIALIZERS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::dataSetup);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        boolean includeServer = event.includeServer();
        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(packOutput, event.getLookupProvider(), "space_marines", existingFileHelper) {
            protected void m_6577_(HolderLookup.Provider p_256380_) {
                this.m_6577_(p_256380_);
            }
        };
        generator.addProvider(includeServer, new PWItemTagsProvider(packOutput, event.getLookupProvider(), blockTagsProvider.m_274426_(), existingFileHelper));
    }
}
