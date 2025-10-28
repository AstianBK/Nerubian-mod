package com.tbk.nerubian;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tbk.nerubian.client.model.ScarabModel;
import com.tbk.nerubian.common.registry.NRegistry;
import com.tbk.nerubian.server.cap.NCapability;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.*;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(NerubianMod.MODID)
public class NerubianMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nerubianmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public NerubianMod(IEventBus modEventBus, ModContainer modContainer) {
        NRegistry.BLOCKS.register(modEventBus);
        NRegistry.ITEMS.register(modEventBus);
        NRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::onRegisterCapabilities);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(NCapability.NERUBIAN_CAP, EntityType.PLAYER, new NerubianCap.NerubianCapProvider());
    }
}
