package org.avp.dimension.varda.gen;

import java.util.Random;

import net.minecraft.world.World;

public class VardaTallTreeGenerator extends VardaTreeGenerator
{
    public VardaTallTreeGenerator(boolean doBlockNotify)
    {
        super(doBlockNotify);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (!isLocationValid(world, x, y, z) || !isLocationValid(world, x + 2, y, z + 2) || !isLocationValid(world, x, y, z + 2) || !isLocationValid(world, x + 2, y, z))
        {
            return false;
        }

        z = z - 4;
        x = x - 4;
        
        this.setBlockAndNotifyAdequately(world, x + 0, y + 10, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 11, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 12, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 13, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 14, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 15, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 16, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 17, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 17, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 17, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 17, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 18, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 18, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 18, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 19, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 9, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 10, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 11, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 12, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 13, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 13, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 14, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 14, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 14, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 15, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 15, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 15, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 16, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 16, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 16, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 17, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 17, z + 4, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 17, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 17, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 17, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 18, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 18, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 18, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 19, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 19, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 2, y + 19, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 0, z + 3, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 0, z + 8, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 5, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 5, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 6, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 6, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 7, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 7, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 8, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 8, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 8, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 9, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 12, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 13, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 13, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 14, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 15, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 16, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 17, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 17, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 17, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 17, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 18, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 18, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 18, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 3, y + 19, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 4, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 0, z + 7, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 1, z + 4, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 1, z + 7, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 6, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 7, z + 5, TREE_LOGS, 4);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 7, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 8, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 11, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 12, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 12, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 13, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 14, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 14, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 15, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 15, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 16, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 16, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 17, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 17, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 17, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 17, z + 9, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 17, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 18, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 18, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 18, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 4, y + 19, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 0, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 1, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 2, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 2, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 3, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 3, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 4, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 4, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 5, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 6, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 6, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 7, z + 7, TREE_LOGS, 8);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 8, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 8, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 8, z + 8, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 9, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 9, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 9, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 9, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 10, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 10, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 10, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 10, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 11, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 11, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 11, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 11, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 12, z + 7, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 12, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 12, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 13, z + 8, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 13, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 13, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 14, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 14, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 15, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 15, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 16, z + 9, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 16, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 2, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 8, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 9, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 10, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 17, z + 11, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 9, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 18, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 19, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 19, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 19, z + 9, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 5, y + 19, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 0, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 1, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 1, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 2, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 2, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 3, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 3, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 4, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 4, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 5, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 5, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 5, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 6, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 6, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 6, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 7, z + 4, TREE_LOGS, 8);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 7, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 7, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 7, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 8, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 8, z + 3, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 8, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 8, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 8, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 9, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 9, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 9, z + 5, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 9, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 10, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 10, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 10, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 10, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 11, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 11, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 11, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 11, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 12, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 12, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 12, z + 4, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 13, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 13, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 13, z + 3, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 14, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 14, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 15, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 15, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 16, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 16, z + 2, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 0, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 1, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 2, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 3, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 9, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 17, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 2, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 18, z + 10, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 19, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 19, z + 2, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 19, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 6, y + 19, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 0, z + 4, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 0, z + 7, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 1, z + 4, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 1, z + 7, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 7, z + 6, TREE_LOGS, 4);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 12, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 13, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 14, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 15, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 16, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 17, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 17, z + 2, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 17, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 17, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 17, z + 9, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 18, z + 1, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 18, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 18, z + 3, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 7, y + 19, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 0, z + 3, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 0, z + 8, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 8, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 13, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 13, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 14, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 15, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 16, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 17, z + 2, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 17, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 17, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 17, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 18, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 18, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 18, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 8, y + 19, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 9, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 10, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 11, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 11, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 12, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 12, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 12, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 13, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 13, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 13, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 14, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 14, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 14, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 15, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 15, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 15, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 16, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 16, z + 6, TREE_LOGS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 16, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 17, z + 4, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 17, z + 5, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 17, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 17, z + 7, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 17, z + 8, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 18, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 18, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 18, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 19, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 19, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 9, y + 19, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 17, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 17, z + 6, TREE_TENDONS, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 17, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 18, z + 5, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 18, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 18, z + 7, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 10, y + 19, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 10, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 11, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 12, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 13, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 14, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 15, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 16, z + 6, TREE_LEAVES, 0);
        this.setBlockAndNotifyAdequately(world, x + 11, y + 17, z + 6, TREE_LEAVES, 0);
        
        return true;
    }
}
