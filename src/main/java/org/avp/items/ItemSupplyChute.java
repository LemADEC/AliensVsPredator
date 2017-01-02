package org.avp.items;

import org.avp.AliensVsPredator;
import org.avp.entities.EntitySupplyChute;
import org.avp.entities.EntitySupplyChuteMarines;
import org.avp.entities.EntitySupplyChuteSeegson;
import org.avp.entities.model.ModelSupplyChute;

import com.arisux.mdxlib.lib.client.TexturedModel;
import com.arisux.mdxlib.lib.world.item.HookedItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSupplyChute extends HookedItem
{
    public static enum SupplyChuteType
    {
        UNBRANDED(0, EntitySupplyChute.class), MARINES(1, EntitySupplyChuteMarines.class), SEEGSON(2, EntitySupplyChuteSeegson.class);

        private int                                id;
        private Class<? extends EntitySupplyChute> entityType;

        private SupplyChuteType(int id, Class<? extends EntitySupplyChute> entityType)
        {
            this.id = id;
            this.entityType = entityType;
        }

        public int id()
        {
            return id;
        }

        public static SupplyChuteType get(int id)
        {
            for (SupplyChuteType type : values())
            {
                if (type.id == id)
                {
                    return type;
                }
            }

            return UNBRANDED;
        }

        public static SupplyChuteType get(Class<? extends EntitySupplyChute> entityType)
        {
            for (SupplyChuteType type : values())
            {
                if (type.getEntityClassType() == entityType)
                {
                    return type;
                }
            }

            return UNBRANDED;
        }

        public static SupplyChuteType get(Block block)
        {
            for (SupplyChuteType type : values())
            {
                if (type.getBlock() == block)
                {
                    return type;
                }
            }

            return UNBRANDED;
        }

        @SideOnly(Side.CLIENT)
        public TexturedModel<ModelSupplyChute> getModel()
        {
            return SupplyChuteType.getModel(this);
        }

        @SideOnly(Side.CLIENT)
        public static TexturedModel<ModelSupplyChute> getModel(SupplyChuteType type)
        {
            switch (type)
            {
                case MARINES:
                    return AliensVsPredator.resources().models().SUPPLY_CHUTE_MARINES;

                case SEEGSON:
                    return AliensVsPredator.resources().models().SUPPLY_CHUTE_SEEGSON;

                default:
                    return AliensVsPredator.resources().models().SUPPLY_CHUTE;

            }
        }

        public Block getBlock()
        {
            return SupplyChuteType.getBlock(this);
        }

        public static Block getBlock(SupplyChuteType type)
        {
            switch (type)
            {
                case MARINES:
                    return AliensVsPredator.blocks().supplyCrateMarines;

                case SEEGSON:
                    return AliensVsPredator.blocks().supplyCrateSeegson;

                default:
                    return AliensVsPredator.blocks().supplyCrate;
            }
        }

        public Class<? extends EntitySupplyChute> getEntityClassType()
        {
            return entityType;
        }

        public EntitySupplyChute createEntity(World world, double x, double y, double z)
        {
            try
            {
                return (this.getEntityClassType().getConstructor(World.class, double.class, double.class, double.class)).newInstance(new Object[] { world, x, y, z });
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    private SupplyChuteType type;

    public ItemSupplyChute(SupplyChuteType type)
    {
        setUnlocalizedName("supplyChute");
        setMaxStackSize(1);
        this.type = type;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.consumeInventoryItem(this))
        {
            int x = (int) (playerIn.posX + 1);
            int y = (int) (playerIn.posY + 80);
            int z = (int) (playerIn.posZ);

            if (!worldIn.isRemote)
            {
                worldIn.setBlock(x, y, z, this.type.getBlock());
            }

            return itemStackIn;
        }
        return itemStackIn;
    }

    public SupplyChuteType getType()
    {
        return type;
    }
}
