package org.avp.items;

import org.avp.AliensVsPredator;
import org.avp.DamageSources;
import org.avp.Sounds;
import org.avp.inventory.container.ContainerWristbracer;

import com.arisux.mdxlib.lib.world.item.HookedItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ItemWristbracer extends HookedItem
{
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (playersWristbracerContainsBlades(player))
        {
            Sounds.SOUND_WEAPON_WRISTBLADES.playSound(entity, 1.0F, 1.0F );
            entity.attackEntityFrom(DamageSources.causeWristbracerDamage(player), getDamageToApply());

            if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode)
            {
                ItemStack bladesStack = getBladesStack(player.getCurrentEquippedItem());
                NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
                NBTTagList items = player.getCurrentEquippedItem().getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

                if (bladesStack != null && !player.worldObj.isRemote)
                {
                    for (int slot = 0; slot < items.tagCount(); slot++)
                    {
                        NBTTagCompound tag = items.getCompoundTagAt(0);

                        if (tag.getByte("Slot") == 0)
                        {
                            items.removeTag(slot);
                            bladesStack.writeToNBT(tag);
                            tag.setShort("Damage", (short) (bladesStack.getCurrentDurability() + 1));
                            tag.setByte("Slot", (byte) slot);
                            items.appendTag(tag);
                        }
                    }
                }

                nbt.setTag("Items", items);
                player.getCurrentEquippedItem().setTagCompound(nbt);
                ((ContainerWristbracer) getNewContainer(player)).saveToNBT();
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    public Object getNewContainer(EntityPlayer player)
    {
        return new ContainerWristbracer(player);
    }

    public static float getDamageToApply()
    {
        return AliensVsPredator.materials().tools().celtic.getDamageVsEntity() * 1.5F;
    }

    public static ItemStack getBladesStack(ItemStack itemstack)
    {
        if (itemstack != null && itemstack.getTagCompound() != null)
        {
            NBTTagList items = itemstack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

            if (items != null)
            {
                for (byte x = 0; x < items.tagCount(); x++)
                {
                    ItemStack stack = ItemStack.loadItemStackFromNBT(items.getCompoundTagAt(x));

                    if (stack != null && items.getCompoundTagAt(x) != null && items.getCompoundTagAt(x).getByte("Slot") == 0)
                    {
                        return stack;
                    }
                }
            }
        }

        return null;
    }

    public static boolean playersWristbracerContainsBlades(EntityPlayer player)
    {
        return player.getCurrentEquippedItem() != null && ItemWristbracer.getBladesStack(player.getCurrentEquippedItem()) != null && ItemWristbracer.getBladesStack(player.getCurrentEquippedItem()).getItem() == AliensVsPredator.items().itemWristbracerBlades;
    }
}
