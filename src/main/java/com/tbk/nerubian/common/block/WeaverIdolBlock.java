package com.tbk.nerubian.common.block;

import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.common.quests.QuestManager;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
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
                e.progressQuest = 0;
                e.currentQuest = null;
            }else {
                NerubianMod.LOGGER.debug("usado y:"+e.currentQuest);
                e.progressQuest = 0;
                e.currentQuest = getRandomQuest(QuestManager.getQuests());
                NerubianMod.LOGGER.debug("nueva mision adquirida :"+e.currentQuest);
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
