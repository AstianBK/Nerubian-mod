package com.tbk.nerubian.common.item;

import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WeaverCocoonItem extends Item {
    public WeaverCocoonItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player){
            NerubianCap.get(player).ifPresent(cap->{
                cap.transformComplete = true;
            });
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
