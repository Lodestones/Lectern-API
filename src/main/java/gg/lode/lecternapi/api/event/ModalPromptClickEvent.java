package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player clicks a CONSUMER button on a modal prompt.
 */
public class ModalPromptClickEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String promptId;
    private final String reference;

    public ModalPromptClickEvent(Player player, String promptId, String reference) {
        super(player, "MODAL_PROMPT_CLICK");
        this.promptId = promptId;
        this.reference = reference;
    }

    public String getPromptId() {
        return promptId;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
