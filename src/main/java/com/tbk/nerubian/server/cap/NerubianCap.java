package com.tbk.nerubian.server.cap;

import com.tbk.nerubian.common.api.INerubian;
import com.tbk.nerubian.common.quests.Quest;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NerubianCap implements INerubian {
    public AnimationState idle = new AnimationState();
    public AnimationState crouching = new AnimationState();
    public AnimationState attack = new AnimationState();
    public AnimationState use = new AnimationState();
    public AnimationState block = new AnimationState();
    public AnimationState burrow = new AnimationState();
    public AnimationState swim = new AnimationState();

    public Quest currentQuest=null;
    public int speechTime=0;
    public int speechTimeO=0;
    public int progressQuest=0;
    public int idleTimer = 0;
    public int nerubianTier = 0;
    public int previousTimesChanged = 0;
    ServerBossEvent event =  (ServerBossEvent)(new ServerBossEvent(Component.translatable("next_wave"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setPlayBossMusic(true).setCreateWorldFog(false);

    public void tick(Player player){
        if(player instanceof ServerPlayer serverPlayer){
            boolean questActive = this.currentQuest!=null;
            event.setVisible(questActive);
            if(questActive){
                if(!event.getPlayers().contains(serverPlayer)){
                    event.addPlayer(serverPlayer);
                }
                Component component = Component.literal(this.currentQuest.getTitle());

                event.setName(component);
                BossEvent.BossBarColor color = getColorForQuestType();
                event.setColor(color);
                event.setProgress((float) this.progressQuest/this.currentQuest.getMaxProgress());

            }else {
                event.removeAllPlayers();
            }

            Inventory inventory = serverPlayer.getInventory();
            if (inventory.getTimesChanged() != previousTimesChanged) {
                previousTimesChanged = inventory.getTimesChanged();
                this.refreshQuest(player);
            }
        }
        if(player.level().isClientSide){
            this.speechTimeO = this.speechTime;

            if(this.speechTime>0){
                this.speechTime--;
            }
            if(this.idleTimer<=0){
                this.idle.start(player.tickCount);
                this.idleTimer = 20;
            }else {
                this.idleTimer--;
            }

            this.crouching.animateWhen(player.isCrouching(),player.tickCount);
            //this.attack.animateWhen(player.getAttackAnim(1.0F)>0,player.tickCount);
            this.swim.animateWhen(player.isSwimming(),player.tickCount);
            this.block.animateWhen(player.getUseItem().getItem() instanceof ShieldItem,player.tickCount);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getAnimSpeech(float partialTick){
        return Mth.lerp(partialTick,this.speechTimeO,this.speechTime) / 160.0F;
    }

    public void refreshQuest(Player player){
        Item itemQuest = BuiltInRegistries.ITEM.get(ResourceLocation.parse(this.currentQuest.getTargetId()));
        for(int i = 0 ; i < player.getInventory().getContainerSize() ; i++){
            ItemStack item = player.getInventory().getItem(i);
            if(item.is(itemQuest)){
                this.progressQuest = Math.min(item.getCount(),this.currentQuest.getMaxProgress());
            }
        }
    }

    private BossEvent.BossBarColor getColorForQuestType() {
        switch (this.currentQuest.getType()){
            case HUNT -> {
                return BossEvent.BossBarColor.RED;
            }
            case COLLECT -> {
                return BossEvent.BossBarColor.GREEN;
            }
            case SACRIFICE -> {
                return BossEvent.BossBarColor.PURPLE;

            }
        }
        return BossEvent.BossBarColor.WHITE;
    }

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {

    }

    public static Optional<NerubianCap> get(Player player){
        return Optional.ofNullable(player.getCapability(NCapability.NERUBIAN_CAP));
    }
    public static class NerubianCapProvider implements ICapabilityProvider{
        public final NerubianCap cap = new NerubianCap();

        @Override
        public @Nullable INerubian getCapability(Object object, Object context) {
            return cap;
        }
    }
}
