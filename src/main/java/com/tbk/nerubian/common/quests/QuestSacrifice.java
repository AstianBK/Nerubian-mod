package com.tbk.nerubian.common.quests;

import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.TierQuest;

public class QuestSacrifice extends Quest{
    public String entityTypeId;
    public QuestSacrifice(TierQuest tier, String entityTypeId) {
        super("To Hunt :", QuestsType.HUNT, "To Hunt :"+entityTypeId.split(":")[1], tier);
        this.entityTypeId = entityTypeId;
    }
    public boolean canAddProgress(String idTarget) {
        return this.entityTypeId.equals(idTarget);
    }
    public int getMaxProgress(){
        return 1;
    }
}
