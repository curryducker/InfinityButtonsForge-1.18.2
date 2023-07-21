package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.CompatBookshelfSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CarpenterBlocks {

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("spruce");
    public static final RegistryObject<Block> BIRCH_BOOKSHELF_SECRET_BUTTON = registerBookshelf("birch");
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("jungle");
    public static final RegistryObject<Block> ACACIA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("acacia");
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF_SECRET_BUTTON = registerBookshelf("dark_oak");
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF_SECRET_BUTTON = registerBookshelf("crimson");
    public static final RegistryObject<Block> WARPED_BOOKSHELF_SECRET_BUTTON = registerBookshelf("warped");

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("woodworks", block));
    }

    private static RegistryObject<Block> registerBookshelf(String wood) {
        return registerBlock("carpenter_" + wood + "_bookshelf_secret_button", () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_bookshelf")));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Carpenter Compat Blocks for Infinity Buttons");
    }
}
