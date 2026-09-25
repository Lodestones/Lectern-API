package gg.lode.lecternapi.api.data;

/**
 * Which monster a player is wearing.
 *
 * <p>Every one of these is the same thing on the client: a rigged model that replaces a player,
 * walks on the same locomotion, takes the same clips and grabs victims the same way. They differ
 * only in the model and the measurements taken off it, so the server side of all of them is one set
 * of calls and this says which.
 *
 * <p>The name carried here is the effect's own, which is what the packet is addressed to. It is not
 * the name anybody says out loud, so it is kept next to the constant rather than derived from it:
 * the Partygoer's effect is {@code partygoer_monster} because {@code partygoer} was taken.
 */
public enum MonsterCostume {

    /** The one with the ball. Everything else here is a costume and nothing more. */
    VERITY("verity_monster"),

    /** Lanky black silhouette with tentacles for arms, out of Garry's Mod. */
    GMOD_ENTITY("gmod_entity"),

    CAPTAIN_CLARK("captain_clark"),

    PARTYGOER("partygoer_monster"),

    SKIN_STEALER("skin_stealer"),

    DEATH_ANGEL("death_angel"),

    TELEBOY("teleboy");

    private final String effect;

    MonsterCostume(String effect) {
        this.effect = effect;
    }

    /** The effect this costume is served by, which is the channel its packets go to. */
    public String effect() {
        return effect;
    }
}
