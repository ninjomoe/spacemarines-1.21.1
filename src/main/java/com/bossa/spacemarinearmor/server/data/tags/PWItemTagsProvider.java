package com.bossa.spacemarinearmor.server.data.tags;

import com.bossa.spacemarinearmor.SpaceMarines;
import com.bossa.spacemarinearmor.common.registry.PWItemTags;
import com.bossa.spacemarinearmor.common.registry.PWItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PWItemTagsProvider extends ItemTagsProvider {
    public PWItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> objectCompletableFuture, CompletableFuture<TagsProvider.TagLookup<Block>> completableFuture, ExistingFileHelper existingFileHelper) {
        super(packOutput, objectCompletableFuture, completableFuture, SpaceMarines.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PWItemTags.FACTION_ARMOR).add(
                PWItems.WARHAMMER_HELMET.get(),
                PWItems.WARHAMMER_CHEST.get(),
                PWItems.WARHAMMER_LEGGINGS.get(),
                PWItems.WARHAMMER_BOOT.get()
        );
    }
}
