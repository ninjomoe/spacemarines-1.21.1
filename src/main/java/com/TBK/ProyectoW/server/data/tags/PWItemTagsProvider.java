//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.TBK.ProyectoW.server.data.tags;

import com.TBK.ProyectoW.common.registry.PWItemTags;
import com.TBK.ProyectoW.common.registry.PWItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PWItemTagsProvider extends ItemTagsProvider {
    public PWItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> objectCompletableFuture, CompletableFuture<TagsProvider.TagLookup<Block>> completableFuture, ExistingFileHelper existingFileHelper) {
        super(packOutput, objectCompletableFuture, completableFuture, "space_marines", existingFileHelper);
    }

    protected void m_6577_(HolderLookup.Provider p_255894_) {
        this.m_206424_(PWItemTags.FACTION_ARMOR).m_255179_(new Item[]{(Item)PWItems.WARHAMMER_HELMET.get(), (Item)PWItems.WARHAMMER_CHEST.get(), (Item)PWItems.WARHAMMER_LEGGINGS.get(), (Item)PWItems.WARHAMMER_BOOT.get()});
    }
}
