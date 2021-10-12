package fr.cdlmt.ecleriacore.managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.block.BlockFace;

import com.google.common.collect.Maps;

public class ItemsManager {
	
	private HashMap<UUID, BlockFace> lastBlockDirection;
	
	public ItemsManager() {
		lastBlockDirection = Maps.newHashMap();
	}

	public HashMap<UUID, BlockFace> getLastBlockDirection() {
		return lastBlockDirection;
	}
}
