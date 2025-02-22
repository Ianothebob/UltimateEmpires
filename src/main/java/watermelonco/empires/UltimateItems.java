package watermelonco.empires;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public class UltimateItems implements PolymerItem {
    public static final RegistryKey<Item> TIME_TRANSFORMER_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(UltimateEmpires.MOD_ID, "time_transformer"));
    public static final Item TIME_TRANSFORMER = register(
            new SimplePolymerItem(new Item.Settings().registryKey(TIME_TRANSFORMER_KEY)),
            TIME_TRANSFORMER_KEY
    );
    public static final RegistryKey<Item> LIFE_FRAGMENT_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(UltimateEmpires.MOD_ID, "time_transformer"));
    public static final Item LIFE_FRAGMENT = register(
            new SimplePolymerItem(new Item.Settings().registryKey(LIFE_FRAGMENT_KEY)),
            LIFE_FRAGMENT_KEY
    );
    public static void initialize() {
    }
    public static Item register(Item item, RegistryKey<Item> registryKey) {
        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);

        // Return the registered item!
        return registeredItem;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.DEBUG_STICK;
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return null;
    }
}
