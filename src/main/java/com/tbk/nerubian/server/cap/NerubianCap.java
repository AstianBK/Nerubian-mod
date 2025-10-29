package com.tbk.nerubian.server.cap;

import com.tbk.nerubian.common.api.INerubian;
import com.tbk.nerubian.common.quests.Quest;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
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
    public int progressQuest=0;
    public int idleTimer = 0;
    public int nerubianTier = 0;
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

        }
        if(player.level().isClientSide){
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
