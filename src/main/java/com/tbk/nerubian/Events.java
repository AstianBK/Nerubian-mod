package com.tbk.nerubian;

import com.tbk.nerubian.common.quests.QuestManager;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = NerubianMod.MODID)
public class Events {
    @SubscribeEvent
    public static void tickEvent(PlayerTickEvent.Post event){
        if(event.getEntity() instanceof Player player){
            NerubianCap.get(player).ifPresent(cap->{
                cap.tick(player);
            });
        }
    }

    @SubscribeEvent
    public static void onDie(LivingDeathEvent event){
        Entity entity = event.getSource().getEntity();
        if(entity instanceof Player player){
            NerubianCap.get(player).ifPresent(e->{
                if(!e.currentQuest.isComplete(e)){
                    NerubianMod.LOGGER.debug("se puede agregar la nueva kill "+event.getEntity().getEncodeId());

                    if(e.currentQuest.canAddProgress(event.getEntity().getEncodeId())){
                        NerubianMod.LOGGER.debug("se mato al enemigo correcto");

                        e.progressQuest++;
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPick(ItemEntityPickupEvent.Pre event){
        NerubianCap.get(event.getPlayer()).ifPresent(e->{
            if(!e.currentQuest.isComplete(e)){
                NerubianMod.LOGGER.debug("se puede agregar el nuevo item"+event.getItemEntity().getItem().getItem());
                if(e.currentQuest.canAddProgress(event.getItemEntity().getItem().getItem().toString())){
                    e.progressQuest = Math.min(e.currentQuest.getMaxProgress(),event.getItemEntity().getItem().getCount()+e.progressQuest);
                }
            }
        });
    }
    @SubscribeEvent
    public static void addQuestsData(AddReloadListenerEvent event){
        event.addListener(new QuestManager());
    }

}
