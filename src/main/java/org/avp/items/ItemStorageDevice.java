package org.avp.items;

import com.arisux.mdxlib.lib.world.item.HookedItem;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemStorageDevice extends HookedItem
{
    private NBTTagList storedNBTTags = new NBTTagList();

    public ItemStorageDevice()
    {
        ;
    }

    public NBTTagList getStoredNBTTags()
    {
        return storedNBTTags;
    }

    @Override
    public void onUpdate(ItemStack itemstack, World worldObj, Entity entity, int par4, boolean par5)
    {
        super.onUpdate(itemstack, worldObj, entity, par4, par5);
    }
}
