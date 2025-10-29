package com.tbk.nerubian.common.quests;

import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.TierQuest;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.network.FriendlyByteBuf;

public class QuestHunt extends Quest{
    public String entityTypeId;
    public int toHuntEntities;
    public QuestHunt(String title, QuestsType type, String description, TierQuest tier, FriendlyByteBuf buf) {
        super(title, QuestsType.HUNT, description, tier);
        this.entityTypeId = buf.readUtf();
        this.toHuntEntities = buf.readInt();
    }
    public String getTargetId(){
        return this.entityTypeId;
    }
    public int getMaxProgress(){
        return this.toHuntEntities;
    }

    public boolean canAddProgress(String idTarget) {
        NerubianMod.LOGGER.debug("target : "+this.entityTypeId);
        return this.entityTypeId.equals(idTarget);
    }
    public boolean isComplete(NerubianCap cap) {
        return cap.progressQuest==this.toHuntEntities;
    }
}
