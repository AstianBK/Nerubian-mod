package com.tbk.nerubian.common.registry;

import com.tbk.nerubian.NerubianMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.*;

public class NRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NerubianMod.MODID);

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(NerubianMod.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(NerubianMod.MODID);


    public static final DeferredBlock<Block> WEAVER_IDOL_BLOCK = BLOCKS.registerSimpleBlock("weaver_idol", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredItem<BlockItem> WEAVER_IDOL_ITEM = ITEMS.registerSimpleBlockItem("weaver_idol_item", WEAVER_IDOL_BLOCK);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nerubianmod")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> WEAVER_IDOL_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(WEAVER_IDOL_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());
}
