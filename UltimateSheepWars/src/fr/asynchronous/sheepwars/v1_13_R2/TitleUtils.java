package fr.asynchronous.sheepwars.v1_13_R2;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.asynchronous.sheepwars.core.version.ATitleUtils;
import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import net.minecraft.server.v1_13_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.PlayerConnection;

public class TitleUtils extends ATitleUtils {

	@Override
	public void titlePacket(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title,
			String subtitle) {
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;

	    PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
	    connection.sendPacket(packetPlayOutTimes);
	    if (subtitle != null) {
	      IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	      PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
	      connection.sendPacket(packetPlayOutSubTitle);
	    }
	    if (title != null) {
	      IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
	      PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
	      connection.sendPacket(packetPlayOutTitle);
	    }
	}

	@Override
	public void tabPacket(Player player, String footer, String header) {
		if (header == null) header = "";
	    if (footer == null) footer = "";

	    IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
	    IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
	    PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
	    PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter();
	    try {
	      Field field = headerPacket.getClass().getDeclaredField("a");
		  field.setAccessible(true);
		  field.set(headerPacket, tabTitle);
	      field = headerPacket.getClass().getDeclaredField("b");
	      field.setAccessible(true);
	      field.set(headerPacket, tabFoot);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      connection.sendPacket(headerPacket);
	    }
	}

	@Override
	public void actionBarPacket(Player player, String message) {
		IChatBaseComponent actionbar = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
	    PacketPlayOutChat actionbarPacket = new PacketPlayOutChat(actionbar, ChatMessageType.a((byte) 2));
	    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(actionbarPacket);
	}
}
