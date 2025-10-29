package com.tbk.nerubian;

public enum TierQuest {
    FIRST_SACRIFICE(0),
    SECOND_SACRIFICE(1),
    THIRD_SACRIFICE(2);
    public final int tier;
    TierQuest(int tier){
        this.tier = tier;
    }

    public static TierQuest getTierForInt(int tier){
        switch (tier){
            case 1 ->{
                return SECOND_SACRIFICE;
            }
            case 2 ->{
                return THIRD_SACRIFICE;
            }
            default -> {
                return FIRST_SACRIFICE;
            }
        }
    }

    public int getInt(){
        return this.tier;
    }
}
