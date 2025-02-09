package xaeroplus.feature.extensions;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xaero.map.gui.GuiSettings;
import xaero.map.gui.ISettingEntry;
import xaero.map.gui.ScreenSwitchSettingEntry;
import xaeroplus.settings.XaeroPlusSettingsReflectionHax;

public class GuiXaeroPlusWorldMapSettings extends GuiSettings {

    public GuiXaeroPlusWorldMapSettings(Screen parent, Screen escapeScreen) {
        super(Component.translatable("gui.xaeroplus.world_map_settings"), parent, escapeScreen);
        var mainSettingsEntries = XaeroPlusSettingsReflectionHax.getWorldMapConfigSettingEntries();
        var chunkHighlightSettingSwitchEntry = GuiXaeroPlusChunkHighlightSettings.getScreenSwitchSettingEntry(parent);
        this.entries = new ISettingEntry[mainSettingsEntries.size() + 1];
        this.entries[0] = chunkHighlightSettingSwitchEntry;
        for (int i = 0; i < mainSettingsEntries.size(); i++) {
            this.entries[i + 1] = mainSettingsEntries.get(i);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int par1, int par2, float par3) {
        this.renderEscapeScreen(guiGraphics, par1, par2, par3);
        super.render(guiGraphics, par1, par2, par3);
    }

    public static ScreenSwitchSettingEntry getScreenSwitchSettingEntry(Screen parent) {
        return new ScreenSwitchSettingEntry(
            "gui.xaeroplus.world_map_settings",
            GuiXaeroPlusWorldMapSettings::new,
            null,
            true
        );
    }
}
