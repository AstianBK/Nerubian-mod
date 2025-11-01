package com.tbk.nerubian.common.quests;

import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.TierQuest;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.network.FriendlyByteBuf;

public class Quest {

    protected String title;
    protected QuestsType type;
    protected String description;
    protected TierQuest tier;
    protected int reputation;
    protected int xp;
    public Quest(String title, QuestsType type, String description, TierQuest tier,int reputation,int xp) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.tier = tier;
        this.reputation = reputation;
        this.xp = xp;
    }
    public int getXp(){
        return this.xp;
    }

    public int getReputation() {
        return reputation;
    }

    public String getTargetId(){
        return null;
    }

    public String getTitle() {
        return title;
    }

    public QuestsType getType() {
        return this.type;
    }

    public String getDescription() {
        return description;
    }

    public TierQuest getTier(){
        return this.tier;
    }

    public int getMaxProgress(){
        return 0;
    }


    public boolean canAddProgress(String idTarget) {
        NerubianMod.LOGGER.debug("can add ");

        return false;
    }
    public boolean isComplete(NerubianCap cap) {
        return false;
    }
}