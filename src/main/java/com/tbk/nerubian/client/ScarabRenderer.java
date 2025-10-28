package com.tbk.nerubian.client;

import com.tbk.nerubian.client.model.ScarabModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class ScarabRenderer<T extends Player,M extends ScarabModel<T>> extends LivingEntityRenderer<T,M> {
    public ScarabRenderer(EntityRendererProvider.Context context,ScarabModel model) {
        super(context, (M) model, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return null;
    }
}
