package gg.lode.lecternapi;

import gg.lode.lecternapi.data.IPacketPlayer;
import gg.lode.lecternapi.manager.IEmoteManager;
import gg.lode.lecternapi.manager.IPacketMenuManager;
import gg.lode.lecternapi.manager.IPingManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface ILecternAPI {

    IPacketPlayer getPlayer(UUID uniqueId);
    IPacketPlayer getPlayer(Player player);

    IPacketMenuManager getPacketMenuManager();
    IPingManager getPingManager();
    IEmoteManager getEmoteManager();

}
