package fr.cdlmt.ecleriacore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cdlmt.ecleriacore.listeners.ItemsListeners;
import fr.cdlmt.ecleriacore.managers.ItemsManager;

public class EcleriaCore extends JavaPlugin {

	/*
	 * 
	 * Ajouter le plugin faction
	 * 
	 */
	
	private static EcleriaCore instance;
	private ItemsManager itemsManager;
	
	public void onEnable() {
		instance = this;
		
		registerManagers();
		registerListeners();
	}
	
	private void registerManagers() {
		itemsManager = new ItemsManager();
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ItemsListeners(), this);
	}

	public static EcleriaCore getInstance() {
		return instance;
	}
	
	public ItemsManager getItemsManager() {
		return itemsManager;
	}
}
