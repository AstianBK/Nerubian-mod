package com.tbk.nerubian.common.block;

import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.common.quests.QuestManager;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.Random;

public class WeaverIdolBlock extends Block {
    public WeaverIdolBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        NerubianCap.get(player).ifPresent(e->{
            if(e.currentQuest!=null && !e.currentQuest.isComplete(e))return;
            if(level.isClientSide){
                e.speechTime = 160;
                e.speechTimeO = 160;
            }
            if(e.currentQuest!=null && e.currentQuest.isComplete(e)){
                e.currentReputation =Math.min(e.currentQuest.getReputation()+e.currentReputation,100) ;
                if(e.currentReputation == 100 && !e.itemTransformDrop){
                    e.itemTransformDrop = true;
                }
                if(e.currentQuest.getType() == QuestsType.COLLECT){
                    int shrink = e.currentQuest.getMaxProgress();
                    Item shrinkItem = BuiltInRegistries.ITEM.get(ResourceLocation.parse(e.currentQuest.getTargetId()));
                    Inventory inventory = player.getInventory();
                    for (ItemStack item : inventory.items){
                        if(item.is(shrinkItem)){
                            int count = item.getCount();
                            item.shrink(Math.min(shrink,count));
                            shrink-=count;
                        }
                        if(shrink<=0){
                            return;
                        }
                    }
                }
                e.progressQuest = 0;
                e.currentQuest = null;
            }else {
                e.timeQuest = 10000;
                e.progressQuest = 0;
                e.currentQuest = getRandomQuest(QuestManager.getQuests());
                e.refreshQuest(player);
            }
        });
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public static <T> T getRandomQuest(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía o ser null.");
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
