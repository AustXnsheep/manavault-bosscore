package austxnsheep.bosscore.Utils.NbtUtil;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public interface NBTUtil {
    default void addCustomNBT(Entity entity, Plugin plugin, String nbtName, String yourdata) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, nbtName);

        dataContainer.set(key, PersistentDataType.STRING, yourdata);
    }
    default String getCustomNBT(Entity entity, Plugin plugin, String nbtName) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, nbtName);

        return dataContainer.getOrDefault(key, PersistentDataType.STRING, null);
    }
}
