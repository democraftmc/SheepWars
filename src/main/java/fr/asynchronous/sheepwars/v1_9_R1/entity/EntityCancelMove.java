package fr.asynchronous.sheepwars.v1_9_R1.entity;

import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.entity.Player;

public class EntityCancelMove extends EntityArmorStand
{
	public Player player;
	
    public EntityCancelMove(final World world) {
        super(world);
    }

	public EntityCancelMove(final Player player) {
		super(((CraftWorld) player.getWorld()).getHandle());
		this.player = player;
		setInvisible(true);
		setSmall(true);
		setGravity(false);
	}

	public void spawnClientEntity() {
		final net.minecraft.server.v1_9_R1.PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(this);
		sendPacket(this.player, packet);
	}

	public void destroyClientEntity() {
		final net.minecraft.server.v1_9_R1.PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(this.getId());
		sendPacket(this.player, packet);
	}

	public void rideClientEntity() {
		this.passengers.add(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer) this.player).getHandle());
		final PacketPlayOutMount packet = new PacketPlayOutMount(this);
		sendPacket(this.player, packet);
	}

	public void updateClientEntityLocation() {
		final Location loc = this.player.getLocation();
		setPositionRotation(loc.getX(), loc.getY() - 1, loc.getZ(), loc.getYaw(), loc.getPitch());

		final net.minecraft.server.v1_9_R1.PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(this);
		sendPacket(this.player, packet);
	}

	public void unrideClientEntity() {
		this.passengers.add(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer) this.player).getHandle());
		final PacketPlayOutMount packet = new PacketPlayOutMount(this);
		sendPacket(this.player, packet);
	}

	private static void sendPacket(Player player, Packet<?> packet) {
		((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}
