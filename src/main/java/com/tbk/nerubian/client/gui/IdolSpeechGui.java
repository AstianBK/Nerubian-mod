package com.tbk.nerubian.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension;

public class IdolSpeechGui implements LayeredDraw.Layer {
    protected static final ResourceLocation[] FRAMES_SPEECH = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_speech_0.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_speech_1.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_speech_2.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_speech_3.png"),
    };
    protected static final ResourceLocation[] FRAMES_BACKGROUND = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_background_0.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_background_1.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_background_2.png"),
            ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"textures/gui/weaver_background_3.png"),
    };

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player==null)return;
        int height = guiGraphics.guiHeight();
        int width = guiGraphics.guiWidth();
        Player player = mc.player;
        float partialTick = deltaTracker.getRealtimeDeltaTicks();
        NerubianCap.get(player).ifPresent(cap->{
            float percent = cap.getAnimSpeech(partialTick);
            if(percent>0){

                int i = width / 2 -140;
                int j1 =  i + 101;
                int k1 = height - 58 ;

                float xExtra = 129;
                float yExtra = 13;

                float centerX = (j1 + xExtra);
                float centerY = (k1 + yExtra);

                int index = (int) ((player.tickCount+partialTick) % 4.0F);
                ResourceLocation locationSpeech = FRAMES_SPEECH[index];
                guiGraphics.blit(locationSpeech, (int) centerX, (int) centerY, 0,0, Mth.ceil(18 + 128*percent),  Mth.ceil(18 + 128*percent), Mth.ceil(18 + 128*percent) ,  Mth.ceil(18 + 128*percent));
                ResourceLocation locationBackground = FRAMES_BACKGROUND[index];
                guiGraphics.blit(locationBackground, (int) centerX, (int) centerY, 0,0, Mth.ceil(18 + 128*percent),  Mth.ceil(18 + 128*percent), Mth.ceil(18 + 128*percent) ,  Mth.ceil(18 + 128*percent));
            }
        });
    }
}
