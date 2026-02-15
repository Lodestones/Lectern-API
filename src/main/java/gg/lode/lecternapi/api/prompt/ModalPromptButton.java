package gg.lode.lecternapi.api.prompt;

/**
 * Represents a button on a modal prompt displayed to the player.
 *
 * @param label the button text
 * @param type  0=CLOSE, 1=LINK, 2=CONSUMER
 * @param data  empty for CLOSE, URL for LINK, reference string for CONSUMER
 * @param r     red color component (0.0-1.0)
 * @param g     green color component (0.0-1.0)
 * @param b     blue color component (0.0-1.0)
 */
public record ModalPromptButton(String label, int type, String data, float r, float g, float b) {

    public static final int TYPE_CLOSE = 0;
    public static final int TYPE_LINK = 1;
    public static final int TYPE_CONSUMER = 2;

    /**
     * Creates a CLOSE button that simply dismisses the modal.
     *
     * @param label the button text
     * @param r     red color component (0.0-1.0)
     * @param g     green color component (0.0-1.0)
     * @param b     blue color component (0.0-1.0)
     */
    public static ModalPromptButton close(String label, float r, float g, float b) {
        return new ModalPromptButton(label, TYPE_CLOSE, "", r, g, b);
    }

    /**
     * Creates a LINK button that opens a URL in the player's browser.
     *
     * @param label the button text
     * @param url   the URL to open
     * @param r     red color component (0.0-1.0)
     * @param g     green color component (0.0-1.0)
     * @param b     blue color component (0.0-1.0)
     */
    public static ModalPromptButton link(String label, String url, float r, float g, float b) {
        return new ModalPromptButton(label, TYPE_LINK, url, r, g, b);
    }

    /**
     * Creates a CONSUMER button that sends a callback to the server with the given reference.
     *
     * @param label     the button text
     * @param reference the reference string sent back to the server
     * @param r         red color component (0.0-1.0)
     * @param g         green color component (0.0-1.0)
     * @param b         blue color component (0.0-1.0)
     */
    public static ModalPromptButton consumer(String label, String reference, float r, float g, float b) {
        return new ModalPromptButton(label, TYPE_CONSUMER, reference, r, g, b);
    }
}
