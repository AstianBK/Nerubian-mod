package com.tbk.nerubian.common.quests;

import com.tbk.nerubian.QuestsType;
import com.tbk.nerubian.TierQuest;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.network.FriendlyByteBuf;

public class QuestCollect extends Quest{
    public String itemId;
    public int toCollect;
    public QuestCollect(String title, QuestsType type, String description, TierQuest tier,int reputation, FriendlyByteBuf buf) {
        super(title, QuestsType.COLLECT, description, tier,reputation);
        this.itemId = buf.readUtf();
        this.toCollect = buf.readInt();
    }
    public String getTargetId(){
        return this.itemId;
    }
    public int getMaxProgress(){
        return this.toCollect;
    }
    public boolean canAddProgress(String idTarget) {
        return this.itemId.equals(idTarget);
    }

    public boolean isComplete(NerubianCap cap) {
        return cap.progressQuest==this.toCollect;
    }
}
