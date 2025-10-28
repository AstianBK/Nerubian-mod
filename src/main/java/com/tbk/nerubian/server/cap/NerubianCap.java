package com.tbk.nerubian.server.cap;

import com.tbk.nerubian.common.api.INerubian;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
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

    public int idleTimer = 0;
    public void leftUse(Player player){
        if(player.level().isClientSide){
            this.use.stop();
            this.use.start(player.tickCount);
        }
    }
    public void tick(Player player){
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
