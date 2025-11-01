package com.tbk.nerubian;

import com.tbk.nerubian.common.quests.QuestManager;
import com.tbk.nerubian.common.registry.NRegistry;
import com.tbk.nerubian.server.cap.NCapability;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.ItemStackedOnOtherEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
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
                if(e.currentQuest!=null && !e.currentQuest.isComplete(e)){
                    if(e.currentQuest.canAddProgress(event.getEntity().getEncodeId())){
                        e.progressQuest++;
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if(event.getEntity().level().isClientSide)return;
        if (!event.isWasDeath()) return;

        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();


        NerubianCap.get(oldPlayer).ifPresent(oldCap->{
            NerubianCap cap = NerubianCap.get(newPlayer).orElse(null);
            if(cap!=null){
                cap.copyFrom(oldCap);
            }
            //cap.init(newPlayer);

            //cap.syncNewPlayer((ServerPlayer) newPlayer,oldCap,true);
        });
    }

    @SubscribeEvent
    public static void onEquip(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        NerubianCap.get(player).ifPresent(nerubianCap -> {
            if(nerubianCap.transformComplete){
                if (event.getSlot().isArmor()) {
                    ItemStack newItem = event.getTo();
                    if (!newItem.isEmpty()) {
                        player.getInventory().add(newItem);
                    }

                    player.setItemSlot(event.getSlot(), ItemStack.EMPTY);
                }
            }
        });
    }

    @SubscribeEvent
    public static void onPick(ItemEntityPickupEvent.Pre event){
        NerubianCap.get(event.getPlayer()).ifPresent(e->{
            if(e.currentQuest!=null && !e.currentQuest.isComplete(e)){
                if(e.currentQuest.canAddProgress(event.getItemEntity().getItem().getItem().toString())){
                    e.refreshQuest(event.getPlayer());
                }
            }
        });
    }

    @SubscribeEvent
    public static void onUse(PlayerInteractEvent.RightClickItem event){
        if(event.getItemStack().is(NRegistry.HUMANITY_TOTEM.get())){
            NerubianCap.get(event.getEntity()).ifPresent(e->{
                e.currentReputation = 0;
                e.transformComplete = false;
                e.itemTransformDrop = false;
                e.progressQuest = 0;
                event.getEntity().kill();
            });
        }

    }

    @SubscribeEvent
    public static void addQuestsData(AddReloadListenerEvent event){
        event.addListener(new QuestManager());
    }

}
