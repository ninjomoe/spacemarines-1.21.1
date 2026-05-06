package com.TBK.ProyectoW.common.items;

public enum Factions {
    NONE("holy_crusader", false, false, false),
    HOLY_CRUSADER("holy_crusader", false, true, false),
    ROYAL_ZEALOT("royal_zealot", true, false, false),
    PROTO_SUIT("proto_suit", true, true, false),
    INFERNAL_CHAOS("infernal_chaos", true, true, false),
    SILVER_SKULL("silver_skull", true, true, false);

    private final String name;
    private final boolean detailGolden;
    private final boolean textureChange;
    private final boolean detailEye;

    private Factions(String name, boolean detailGolden, boolean textureChange, boolean detailEye) {
        this.name = name;
        this.detailGolden = detailGolden;
        this.textureChange = textureChange;
        this.detailEye = detailEye;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasDetailGolden() {
        return this.detailGolden;
    }

    public boolean hasDetailEyes() {
        return this.detailEye;
    }

    public boolean hasChangeTextures() {
        return this.textureChange;
    }

    public static Factions getForName(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return NONE;
        }
    }
}
