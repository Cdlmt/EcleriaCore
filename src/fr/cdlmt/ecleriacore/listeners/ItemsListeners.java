package fr.cdlmt.ecleriacore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.cdlmt.ecleriacore.EcleriaCore;
import fr.cdlmt.ecleriacore.managers.ItemsManager;

public class ItemsListeners implements Listener {
	
	private ItemsManager itemsManager;
	
	public ItemsListeners() {
		itemsManager = EcleriaCore.getInstance().getItemsManager();
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		ItemStack itemStack = player.getItemInHand();
		
		if(itemStack.getType() != Material.HAMMER) return;
		
		if(!itemsManager.getLastBlockDirection().containsKey(player.getUniqueId())) return;
		
		Block block = event.getBlock();
		
		if (itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.DOWN || itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.UP) {
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH));
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH_EAST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.EAST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH_EAST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH_WEST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.WEST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH_WEST));
        } else if (itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.EAST || itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.WEST) {
            this.breakBlock(itemStack, block.getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.DOWN));
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH));
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH).getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN));
        } else if (itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.NORTH || itemsManager.getLastBlockDirection().get(player.getUniqueId()) == BlockFace.SOUTH) {
            this.breakBlock(itemStack, block.getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.DOWN));
            this.breakBlock(itemStack, block.getRelative(BlockFace.EAST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.WEST));
            this.breakBlock(itemStack, block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN));
            this.breakBlock(itemStack, block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP));
            this.breakBlock(itemStack, block.getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN));
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) itemsManager.getLastBlockDirection().put(e.getPlayer().getUniqueId(), e.getBlockFace());
    }
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(itemsManager.getLastBlockDirection().containsKey(event.getPlayer().getUniqueId())) itemsManager.getLastBlockDirection().remove(event.getPlayer().getUniqueId());
	}
	
	@SuppressWarnings("deprecation")
	public void breakBlock(ItemStack itemStack, Block block) {
		if(block.getType() == Material.BEDROCK || block.getType() == Material.STATIONARY_WATER || block.getType() == Material.WATER || block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.OBSIDIAN || block.getType() == Material.MOB_SPAWNER || block.getType() == Material.ICE || block.getType() == Material.PACKED_ICE || block.getType() == Material.ENDER_CHEST) return;
		
		if(Board.getInstance().getFactionAt(new FLocation(block)) != null && Board.getInstance().getFactionAt(new FLocation(block)) != Factions.getInstance().getNone()) return;
		
		block.breakNaturally(itemStack);
	}
}
