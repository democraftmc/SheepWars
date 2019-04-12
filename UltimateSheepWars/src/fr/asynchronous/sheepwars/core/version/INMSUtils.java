package fr.asynchronous.sheepwars.core.version;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public interface INMSUtils {
	
	public void setKiller(final Entity entity, final Entity killer);
	
	public void setItemInHand(final ItemStack item, final Player player);
	
	public ItemStack setIllegallyGlowing(final ItemStack item, boolean activate);
	
	public void setUnbreakable(final ItemMeta meta, final boolean bool);
	
	public void setHealth(final LivingEntity ent, final Double health);
	
	public void displayRedScreen(final Player player, final boolean activate);
	
	public MaterialData getDye(final DyeColor color);
	
	public void cancelMove(final Player player, final boolean bool);
	
}
