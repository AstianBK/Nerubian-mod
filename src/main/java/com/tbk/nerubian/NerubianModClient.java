package com.tbk.nerubian;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tbk.nerubian.client.ScarabRenderer;
import com.tbk.nerubian.client.gui.IdolSpeechGui;
import com.tbk.nerubian.client.layer.ItemScarabLayer;
import com.tbk.nerubian.client.model.ScarabModel;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.GuiLayerManager;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import static net.minecraft.client.renderer.entity.LivingEntityRenderer.isEntityUpsideDown;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = NerubianMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = NerubianMod.MODID, value = Dist.CLIENT)
public class NerubianModClient {
    public NerubianModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.AddLayers event) {

    }

    @SubscribeEvent
    public static void registerModel(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ScarabModel.LAYER_LOCATION,ScarabModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void renderModel(RenderLivingEvent.Pre event){
        if (event.getEntity() instanceof Player){
            ScarabModel model = new ScarabModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ScarabModel.LAYER_LOCATION));
            ScarabRenderer renderer = new ScarabRenderer(new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(),Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getBlockRenderer(),Minecraft.getInstance().gameRenderer.itemInHandRenderer,Minecraft.getInstance().getResourceManager(),Minecraft.getInstance().getEntityModels(),Minecraft.getInstance().font),model);
            ItemScarabLayer layer = new ItemScarabLayer<>(renderer,Minecraft.getInstance().gameRenderer.itemInHandRenderer);
            Player player = (Player) event.getEntity();
            PoseStack poseStack = event.getPoseStack();
            float partialTicks = event.getPartialTick();
            int light = event.getPackedLight();
            MultiBufferSource bufferSource = event.getMultiBufferSource();
            event.setCanceled(true);
            poseStack.pushPose();
            model.attackTime = player.getAttackAnim(partialTicks);
            boolean shouldSit = player.isPassenger() && (player.getVehicle() != null && player.getVehicle().shouldRiderSit());
            float f = Mth.rotLerp(partialTicks, player.yBodyRotO, player.yBodyRot);
            float f1 = Mth.rotLerp(partialTicks, player.yHeadRotO, player.yHeadRot);
            float f2 = f1 - f;
            float f7;
            if (shouldSit && player.getVehicle() instanceof LivingEntity livingplayer) {
                f = Mth.rotLerp(partialTicks, livingplayer.yBodyRotO, livingplayer.yBodyRot);
                f2 = f1 - f;
                f7 = Mth.wrapDegrees(f2);
                if (f7 < -85.0F) {
                    f7 = -85.0F;
                }

                if (f7 >= 85.0F) {
                    f7 = 85.0F;
                }

                f = f1 - f7;
                if (f7 * f7 > 2500.0F) {
                    f += f7 * 0.2F;
                }

                f2 = f1 - f;
            }

            float f6 = Mth.lerp(partialTicks, player.xRotO, player.getXRot());
            if (isEntityUpsideDown(player)) {
                f6 *= -1.0F;
                f2 *= -1.0F;
            }

            f2 = Mth.wrapDegrees(f2);
            if (player.hasPose(Pose.SLEEPING)) {
                Direction direction = player.getBedOrientation();
                if (direction != null) {
                    float f3 = player.getEyeHeight(Pose.STANDING) - 0.1F;
                    poseStack.translate((float)(-direction.getStepX()) * f3, 0.0F, (float)(-direction.getStepZ()) * f3);
                }
            }

            float f8 = player.getScale();
            poseStack.scale(f8, f8, f8);
            float f9 = player.tickCount + partialTicks;
            setupRotations(player,poseStack,f9, f, partialTicks, f8);
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            poseStack.translate(0.0F, -1.501F, 0.0F);
            float f4 = 0.0F;
            float f5 = 0.0F;
            if (!shouldSit && player.isAlive()) {
                f4 = player.walkAnimation.speed(partialTicks);
                f5 = player.walkAnimation.position(partialTicks);
                if (player.isBaby()) {
                    f5 *= 3.0F;
                }

                if (f4 > 1.0F) {
                    f4 = 1.0F;
                }
            }

            model.prepareMobModel(player,f5,f6,partialTicks);
            model.setupAnim(player,f5, f4, f9, f2, f6);
            model.renderToBuffer(poseStack,bufferSource.getBuffer(RenderType.entityCutoutNoCull(Util.TEXTURE)),light, OverlayTexture.NO_OVERLAY);
            layer.render(poseStack,bufferSource,event.getPackedLight(),player,f5,f6,partialTicks,f9,0.0F,0.0F);
            poseStack.popPose();
        }
    }

    protected static void setupRotations(Player entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        if (entity.isFullyFrozen()) {
            yBodyRot += (float)(Math.cos((double)entity.tickCount * 3.25) * Math.PI * 0.4000000059604645);
        }

        if (!entity.hasPose(Pose.SLEEPING)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yBodyRot));
        }

        if (entity.deathTime > 0) {
            float f = ((float)entity.deathTime + partialTick - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }

            poseStack.mulPose(Axis.ZP.rotationDegrees(f * 90));
        } else if (entity.isAutoSpinAttack()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F - entity.getXRot()));
            poseStack.mulPose(Axis.YP.rotationDegrees(((float)entity.tickCount + partialTick) * -75.0F));
        } else if (entity.hasPose(Pose.SLEEPING)) {
            Direction direction = entity.getBedOrientation();
            float f1 = direction != null ? sleepDirectionToRotation(direction) : yBodyRot;
            poseStack.mulPose(Axis.YP.rotationDegrees(f1));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
        } else if (isEntityUpsideDown(entity)) {
            poseStack.translate(0.0F, (entity.getBbHeight() + 0.1F) / scale, 0.0F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }

    }

    private static float sleepDirectionToRotation(Direction facing) {
        switch (facing) {
            case SOUTH:
                return 90.0F;
            case NORTH:
                return 270.0F;
            case EAST:
                return 180.0F;
            default:
                return 0.0F;
        }
    }


    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR, ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID,"idol_speech"),new IdolSpeechGui());
    }

}
