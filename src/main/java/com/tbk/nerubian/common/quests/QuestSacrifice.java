package com.tbk.nerubian.common.quests;

import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.TierQuest;
import net.minecraft.network.FriendlyByteBuf;

public class QuestSacrifice extends Quest{
    public String entityTypeId;
    public QuestSacrifice(String title, QuestsType type, String description, TierQuest tier,int reputation, FriendlyByteBuf buf) {
        super(title, QuestsType.COLLECT, description, tier,reputation);
        this.entityTypeId = buf.readUtf();
    }
    public boolean canAddProgress(String idTarget) {
        return this.entityTypeId.equals(idTarget);
    }
    public int getMaxProgress(){
        return 1;
    }
}
