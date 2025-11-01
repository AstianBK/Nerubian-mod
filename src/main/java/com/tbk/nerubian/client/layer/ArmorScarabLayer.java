package com.tbk.nerubian.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tbk.nerubian.client.model.ScarabModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.player.Player;

public class ArmorScarabLayer<T extends Player,M extends ScarabModel<T>> extends RenderLayer<T,M> {
    public ArmorScarabLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
