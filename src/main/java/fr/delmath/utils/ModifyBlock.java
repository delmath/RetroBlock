package fr.delmath.utils;

public class ModifyBlock {
    private String coordonnee;
    private String blockAvant;

    public ModifyBlock(String coordonnee, String blockAvant) {
        this.coordonnee = coordonnee;
        this.blockAvant = blockAvant;
    }

    public String getCoordonnee() {
        return coordonnee;
    }

    public String getBlockAvant() {
        return blockAvant;
    }
}