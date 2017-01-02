package org.avp.gui;

import java.util.ArrayList;

import org.avp.AliensVsPredator;
import org.avp.api.AssemblerAPI.AssemblerSchematic;
import org.avp.entities.tile.TileEntityAssembler;
import org.avp.packets.server.PacketAssembleCurrentSchematic;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.arisux.mdxlib.lib.client.gui.GuiCustomButton;
import com.arisux.mdxlib.lib.client.gui.IAction;
import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.client.render.OpenGL;
import com.arisux.mdxlib.lib.game.Game;
import com.arisux.mdxlib.lib.world.entity.player.inventory.Inventories;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiAssembler extends GuiContainer
{
    private ArrayList<AssemblerSchematic> schematics;
    private GuiCustomButton buttonScrollUp;
    private GuiCustomButton buttonScrollDown;
    private GuiCustomButton buttonAssemble;
    private GuiCustomButton buttonAssemble4;
    private GuiCustomButton buttonAssemble8;
    private GuiCustomButton buttonAssemble16;
    private GuiCustomButton buttonAssemble32;
    private GuiCustomButton buttonAssembleStack;
    private int scroll = 0;
    private boolean hasMaterials = false;

    public GuiAssembler(InventoryPlayer invPlayer, TileEntityAssembler assembler, World world, int x, int y, int z)
    {
        super(assembler.getNewContainer(invPlayer.player));
        this.schematics = AliensVsPredator.assembler().getSchematicRegistry();

        this.xSize = 256;
        this.ySize = 170;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.guiTop = 5;

        this.buttonScrollUp = new GuiCustomButton(0, 0, 0, 20, 20, "", null);
        this.buttonScrollDown = new GuiCustomButton(1, 0, 0, 20, 20, "", null);
        this.buttonAssemble = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
        this.buttonAssemble4 = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
        this.buttonAssemble8 = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
        this.buttonAssemble16 = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
        this.buttonAssemble32 = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
        this.buttonAssembleStack = new GuiCustomButton(2, 0, 0, 50, 20, "", null);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        if (schematics != null)
        {
            AssemblerSchematic selectedSchematic = schematics.get(getScroll());

            if (selectedSchematic != null)
            {
                int curStack = -1;
                int progress = 0;
                int maxProgress = 0;

                for (ItemStack stack : selectedSchematic.getItemsRequired())
                {
                    curStack++;
                    int amountOfStack = Inventories.getAmountOfItemPlayerHas(stack.getItem(), Game.minecraft().thePlayer);
                    boolean playerHasItemstack = amountOfStack > 0;
                    int stackY = this.ySize + 12 + (curStack * 12);
                    int curStackSize = (amountOfStack > stack.stackSize ? stack.stackSize : amountOfStack);
                    Draw.drawRect(2, stackY - 2, this.xSize - 4, 12, 0x11FFFFFF);
                    Draw.drawString(curStackSize + "/" + stack.stackSize, 220, stackY, curStackSize >= stack.stackSize ? 0xFF00AAFF : curStackSize < stack.stackSize && curStackSize > 0 ? 0xFFFFAA00 : 0xFF888888);
                    Draw.drawString(stack.getDisplayName(), 20, stackY, 0xFF888888);
                    Draw.drawItemIcon(stack.getItem(), 5, stackY, 8, 8);

                    maxProgress += stack.stackSize;

                    if (playerHasItemstack)
                    {
                        progress += amountOfStack > stack.stackSize ? stack.stackSize : amountOfStack;
                    }
                }

                int percentComplete = (progress * 100 / maxProgress);
                Draw.drawProgressBar("" + progress + " of " + maxProgress + " Materials / " + percentComplete + "% Complete", maxProgress, progress, 0, this.ySize - 4, this.xSize, 7, 3, percentComplete < 25 ? 0xFFFF2222 : percentComplete < 50 ? 0xFFFFAA00 : (percentComplete == 100 ? 0xFF00AAFF : 0xFFFFAA00), false);

                if (percentComplete == 100)
                {
                    this.hasMaterials = true;
                }
                else
                {
                    this.hasMaterials = false;
                }
            }

            /**
             * Draw the schematics in the assembler
             */
            int curItem = -1;

            for (AssemblerSchematic schematic : schematics)
            {
                if (schematic != null && schematic.getItemStackAssembled() != null)
                {
                    Item item = schematic.getItemStackAssembled().getItem();

                    if (item != null)
                    {
                        curItem++;
                        int numberRendered = curItem - (getScroll());
                        int entryX = 4;
                        int entryY = 20 + (numberRendered) * 11;

                        if (numberRendered >= 0 && numberRendered <= 10)
                        {
                            OpenGL.enableBlend();
                            OpenGL.disableBlend();
                            Draw.drawString((curItem + 1) + " " + StatCollector.translateToLocal(item.getUnlocalizedName() + ".name"), entryX + 13, entryY + 2, curItem == this.scroll ? 0xFF00AAFF : 0xFF555555, false);
                            Draw.drawItemIcon(item, entryX + 2, entryY + 2, 8, 8);
                        }
                    }
                }
            }
        }

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        AliensVsPredator.resources().GUI_ASSEMBLER.bind();
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartial)
    {
        super.drawScreen(mouseX, mouseY, renderPartial);
        
        int buttonWidth = 38;
        int buttonOffsetX = buttonWidth + 9;
        int offset = 0;

        this.buttonScrollUp.xPosition = this.guiLeft + xSize + 5 - buttonOffsetX;
        this.buttonScrollUp.yPosition = this.guiTop + 4;
        this.buttonScrollUp.width = buttonWidth;
        this.buttonScrollUp.height = 19;
        this.buttonScrollUp.displayString = "\u21e7";
        this.buttonScrollUp.baseColor = this.getScroll() == 0 ? 0x22000000 : 0xAA000000;
        this.buttonScrollUp.drawButton();
        this.buttonScrollUp.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                scrollDown();
            }
        });

        this.buttonAssemble.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssemble.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssemble.displayString = "\u2692 x1";
        this.buttonAssemble.width = buttonWidth;
        this.buttonAssemble.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssemble.drawButton();
        this.buttonAssemble.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 1));
            }
        });

        this.buttonAssemble4.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssemble4.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssemble4.displayString = "\u2692 x4";
        this.buttonAssemble4.width = buttonWidth;
        this.buttonAssemble4.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssemble4.drawButton();
        this.buttonAssemble4.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 4));
            }
        });

        this.buttonAssemble8.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssemble8.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssemble8.displayString = "\u2692 x8";
        this.buttonAssemble8.width = buttonWidth;
        this.buttonAssemble8.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssemble8.drawButton();
        this.buttonAssemble8.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 8));
            }
        });

        this.buttonAssemble16.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssemble16.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssemble16.displayString = "\u2692 x16";
        this.buttonAssemble16.width = buttonWidth;
        this.buttonAssemble16.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssemble16.drawButton();
        this.buttonAssemble16.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 16));
            }
        });

        this.buttonAssemble32.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssemble32.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssemble32.displayString = "\u2692 x32";
        this.buttonAssemble32.width = buttonWidth;
        this.buttonAssemble32.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssemble32.drawButton();
        this.buttonAssemble32.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 32));
            }
        });

        this.buttonAssembleStack.xPosition = (this.guiLeft + this.xSize + 5) - buttonOffsetX;
        this.buttonAssembleStack.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonAssembleStack.displayString = "\u2692 x64";
        this.buttonAssembleStack.width = buttonWidth;
        this.buttonAssembleStack.baseColor = this.hasMaterials ? 0xAA000000 : 0x22000000;
        this.buttonAssembleStack.drawButton();
        this.buttonAssembleStack.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                AssemblerSchematic selectedSchematic = schematics.get(getScroll());
                AliensVsPredator.assembler().assembleSchematicAsPlayer(selectedSchematic, Game.minecraft().thePlayer);
                AliensVsPredator.network().sendToServer(new PacketAssembleCurrentSchematic(selectedSchematic.getSchematicId(), 64));
            }
        });

        this.buttonScrollDown.xPosition = this.guiLeft + this.xSize + 5 - buttonOffsetX;
        this.buttonScrollDown.yPosition = this.guiTop + 3 + (offset += 20);
        this.buttonScrollDown.width = buttonWidth;
        this.buttonScrollDown.height = 19;
        this.buttonScrollDown.displayString = "\u21e9";
        this.buttonScrollDown.baseColor = this.getScroll() >= (this.schematics.size() - 1) ? 0x22000000 : 0xAA000000;
        this.buttonScrollDown.drawButton();
        this.buttonScrollDown.setAction(new IAction()
        {
            @Override
            public void perform(GuiCustomButton button)
            {
                scrollUp();
            }
        });
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        int dWheel = Mouse.getDWheel();

        if (dWheel > 0)
        {
            scrollDown();
        }
        else if (dWheel < 0)
        {
            scrollUp();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            scrollDown();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            scrollUp();
        }
    }

    public void scrollDown()
    {
        if (this.scroll >= 1)
        {
            this.scroll -= 1;
        }
    }

    public void scrollUp()
    {
        if (this.scroll < this.schematics.size() - 1)
        {
            this.scroll += 1;
        }
    }

    public int getScroll()
    {
        return this.scroll;
    }
}
