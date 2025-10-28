package com.tbk.nerubian.common.quests;

import net.minecraft.network.FriendlyByteBuf;

public abstract class Quest {

    private String title;
    private UnlockLevel unlockLevel;
    private String description;
    private String entityId;
    private float extraYRot;
    private int orden;
    public Quest() {}

    public Quest(String title, UnlockLevel unlockLevel, String dialogs, String entityId, float extraYRot, int orden) {
        this.title = title;
        this.unlockLevel= unlockLevel;
        this.description = dialogs;
        this.entityId = entityId;
        this.extraYRot = extraYRot;
        this.orden = orden;
    }

    public String getTitle() {
        return title;
    }

    public UnlockLevel getUnlockLevel() {
        return this.unlockLevel;
    }

    public String getDescription() {
        return description;
    }

    public String getEntityId() {
        return entityId;
    }

    public float getExtraYRot() {
        return extraYRot;
    }

    public int getOrden() {
        return orden;
    }

    public static void encode(Quest page, FriendlyByteBuf packetBuffer) {
        packetBuffer.writeUtf(page.title);
        packetBuffer.writeUtf(page.getUnlockLevel().name());

        packetBuffer.writeUtf(page.description);
        packetBuffer.writeUtf(page.entityId);
        packetBuffer.writeFloat(page.extraYRot);
        packetBuffer.writeInt(page.orden);
    }

    public static Quest decode(FriendlyByteBuf packetBuffer) {
        String title = packetBuffer.readUtf();
        UnlockLevel unlockLevel = UnlockLevel.valueOf(packetBuffer.readUtf());
        String description = packetBuffer.readUtf();
        String entityId = packetBuffer.readUtf();
        float extraYRot = packetBuffer.readFloat();
        int orden = packetBuffer.readInt();
        return new Quest(title, unlockLevel, description,entityId,extraYRot,orden);
    }

    public enum UnlockLevel {
        OVERWORLD,
        NETHER,
        END;

        public boolean isUnlocked(UnlockLevel level){
            if(this==OVERWORLD){
                return true;
            }else if(this==NETHER){
                return level==NETHER || level==END;
            }else {
                return level==END;
            }
        }
    }
}