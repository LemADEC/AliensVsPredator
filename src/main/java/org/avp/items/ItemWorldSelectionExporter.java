package org.avp.items;

import java.util.List;

import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.item.HookedItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemWorldSelectionExporter extends HookedItem
{
    public ItemWorldSelectionExporter()
    {
        super();
        this.maxStackSize = 1;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int posX, int posY, int posZ, EntityPlayer player)
    {
        if (!player.worldObj.isRemote)
        {
            player.worldObj.markBlockForUpdate(posX, posY, posZ);
            this.writeSelectionDataToStack(new CoordData(posX, posY, posZ, player.worldObj.getBlock(posX, posY, posZ)), null, stack);
        }

        return true;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.worldObj.isRemote)
        {
            this.writeSelectionDataToStack(null, new CoordData(posX, posY, posZ, player.worldObj.getBlock(posX, posY, posZ)), stack);
        }

        return super.onItemUseFirst(stack, player, world, posX, posY, posZ, side, hitX, hitY, hitZ);
    }

    public void writeSelectionDataToStack(CoordData pos1, CoordData pos2, ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound() != null ? stack.getTagCompound() : new NBTTagCompound();
        NBTTagCompound lastSelection1 = (NBTTagCompound) tag.getTag("SelectedPos1");
        NBTTagCompound lastSelection2 = (NBTTagCompound) tag.getTag("SelectedPos2");
        CoordData lastPos1 = lastSelection1 != null ? new CoordData(lastSelection1.getInteger("PosX"), lastSelection1.getInteger("PosY"), lastSelection1.getInteger("PosZ"), lastSelection1.getString("Id")) : null;
        CoordData lastPos2 = lastSelection2 != null ? new CoordData(lastSelection2.getInteger("PosX"), lastSelection2.getInteger("PosY"), lastSelection2.getInteger("PosZ"), lastSelection2.getString("Id")) : null;

        if (pos1 != null)
        {
            tag.setTag("SelectedPos1", pos1.writeToNBT());
        }

        if (pos2 != null)
        {
            tag.setTag("SelectedPos2", pos2.writeToNBT());
        }

        stack.setTagCompound(tag);

        if (lastPos1 != null && lastPos2 != null)
        {
            stack.setStackDisplayName(String.format("World Selector - Pos1(%s, %s, %s) - Pos2(%s, %s, %s)", lastPos1.x, lastPos1.y, lastPos1.z, lastPos2.x, lastPos2.y, lastPos2.z));
        }
    }

    @Override
    @SuppressWarnings("all")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityPlayer, List list, boolean par4)
    {
        list.add("World Selection Exporter");
    }
}
