package com.tbk.nerubian.server.cap;

import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.common.api.INerubian;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.CapabilityListenerHolder;
import net.neoforged.neoforge.capabilities.CapabilityRegistry;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class NCapability {
    public static final EntityCapability<NerubianCap,Void> NERUBIAN_CAP = EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"nerubian_cap"), NerubianCap.class);
}
