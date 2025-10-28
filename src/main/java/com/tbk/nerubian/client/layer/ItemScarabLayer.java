package com.tbk.nerubian.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.NerubianModClient;
import com.tbk.nerubian.client.model.ScarabModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Quaternionf;

public class ItemScarabLayer<T extends Player,M extends ScarabModel<T>> extends RenderLayer<T,M> {
    private final ItemInHandRenderer itemInHandRenderer;

    public ItemScarabLayer(RenderLayerParent renderer,ItemInHandRenderer item) {
        super(renderer);
        this.itemInHandRenderer = item;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderItem(poseStack,bufferSource,packedLight,livingEntity);
    }

    protected void renderArmWithItem(LivingEntity p_270884_, ItemStack p_270379_, ItemDisplayContext p_270607_, HumanoidArm p_270324_, PoseStack p_270124_, MultiBufferSource p_270414_, int p_270295_) {
        if (p_270379_.is(Items.SPYGLASS) && p_270884_.getUseItem() == p_270379_ && p_270884_.swingTime == 0) {
            this.renderArmWithSpyglass(p_270884_, p_270379_, p_270324_, p_270124_, p_270414_, p_270295_);
        } else {
            if (!p_270379_.isEmpty()) {
                p_270124_.pushPose();
                this.getParentModel().translateToHand(p_270124_,p_270324_ == HumanoidArm.LEFT);
                p_270124_.mulPose(Axis.XP.rotationDegrees(-90.0F));
                p_270124_.mulPose(Axis.YP.rotationDegrees(180.0F));
                boolean flag = p_270324_ == HumanoidArm.LEFT;
                p_270124_.translate((float)(flag ? -9 : 9) / 16.0F, 0.35F, -0.225F);
                p_270124_.mulPose(Axis.XP.rotationDegrees(45));
                this.itemInHandRenderer.renderItem(p_270884_, p_270379_, p_270607_, flag, p_270124_, p_270414_, p_270295_);
                p_270124_.popPose();
            }
        }

    }



    private void renderArmWithSpyglass(LivingEntity p_174518_, ItemStack p_174519_, HumanoidArm p_174520_, PoseStack p_174521_, MultiBufferSource p_174522_, int p_174523_) {
        p_174521_.pushPose();
        ModelPart modelpart = this.getParentModel().getHead();
        float f = modelpart.xRot;
        modelpart.xRot = Mth.clamp(modelpart.xRot, (-(float)Math.PI / 6F), ((float)Math.PI / 2F));
        modelpart.translateAndRotate(p_174521_);
        modelpart.xRot = f;
        CustomHeadLayer.translateToHead(p_174521_, false);
        boolean flag = p_174520_ == HumanoidArm.LEFT;
        p_174521_.translate((flag ? -2.5F : 2.5F) / 16.0F, -0.0625F, 0.0F);
        this.itemInHandRenderer.renderItem(p_174518_, p_174519_, ItemDisplayContext.HEAD, false, p_174521_, p_174522_, p_174523_);
        p_174521_.popPose();
    }


    public void renderItem(PoseStack p_117204_, MultiBufferSource p_117205_, int p_117206_, T p_117207_) {
        boolean flag = p_117207_.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? p_117207_.getOffhandItem() : p_117207_.getMainHandItem();
        ItemStack itemstack1 = flag ? p_117207_.getMainHandItem() : p_117207_.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            p_117204_.pushPose();

            this.renderArmWithItem(p_117207_, itemstack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, p_117204_, p_117205_, p_117206_);
            this.renderArmWithItem(p_117207_, itemstack1, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, p_117204_, p_117205_, p_117206_);
            p_117204_.popPose();
        }
    }

}
