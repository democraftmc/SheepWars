package fr.asynchronous.sheepwars.core.kit;

import fr.asynchronous.sheepwars.core.legacy.LegacyItem;
import fr.asynchronous.sheepwars.core.legacy.LegacyMaterial;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import fr.asynchronous.sheepwars.api.util.ItemBuilder;
import fr.asynchronous.sheepwars.api.SheepWarsKit;
import fr.asynchronous.sheepwars.core.message.Message.Messages;

public class NoneKit extends SheepWarsKit {

	public NoneKit() {
		super(8, Messages.KIT_NULL_NAME, true, new ItemBuilder(new LegacyItem(LegacyMaterial.STAINED_GLASS_PANE, DyeColor.RED, 1, (byte) DyeColor.RED.ordinal())), new NullKitLevel());
	}

	public static class NullKitLevel extends SheepWarsKitLevel {

		public NullKitLevel() {
			super(Messages.KIT_NULL_DESCRIPTION, "", 0, 0);
		}

		@Override
		public boolean onEquip(Player player) {
			return true;
		}
	}
}
