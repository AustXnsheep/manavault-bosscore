package austxnsheep.bosscore.NbtUtil;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public interface NBTUtil {
    default void addCustomNBT(Entity entity, Plugin plugin, String yourData) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, "CustomBossType");

        dataContainer.set(key, PersistentDataType.STRING, yourData);
    }
    default String getCustomNBT(Entity entity, Plugin plugin) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, "CustomBossType");

        return dataContainer.getOrDefault(key, PersistentDataType.STRING, null);
    }
}
