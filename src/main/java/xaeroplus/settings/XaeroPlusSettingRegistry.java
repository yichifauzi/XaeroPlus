package xaeroplus.settings;

import xaeroplus.module.ModuleManager;
import xaeroplus.module.impl.NewChunks;
import xaeroplus.util.WDLHelper;

import static xaeroplus.settings.XaeroPlusSettingsReflectionHax.SettingLocation;
import static xaeroplus.settings.XaeroPlusSettingsReflectionHax.markChunksDirtyInWriteDistance;

/**
 * Registry for XaeroPlus-specific settings
 */
public final class XaeroPlusSettingRegistry {

    /**
     * The order settings are defined here determines the order in the settings GUI's.
     */

    public static final XaeroPlusBooleanSetting fastMapSetting = XaeroPlusBooleanSetting.create("Fast Mapping",
                    "Increases mapping speed, might hurt FPS. Adjust rate limit and delay to regain FPS.",
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting mapWriterDelaySetting = XaeroPlusFloatSetting.create("Fast Mapping Delay",
            10, 1000, 10,
                    "Fast Mapping must be enabled. \\n " +
                    "This is roughly the delay in milliseconds between minimap update operations, both render and actual file writes.",
            50, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting fastMapMaxTilesPerCycle = XaeroPlusFloatSetting.create("Fast Mapping Rate Limit",
            10, 120, 10,
                    "Fast Mapping must be enabled. \\n " +
                    "Limits how many chunks can be written in a single cycle. Lower values improve FPS at high render distances.",
            50, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting transparentObsidianRoofSetting = XaeroPlusBooleanSetting.create("Transparent Obsidian Roof",
                    "Makes obsidian placed at build height transparent. Does not update tiles already mapped - you need to remap them.",
            (v) -> markChunksDirtyInWriteDistance(),
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting transparentObsidianRoofDarkeningSetting = XaeroPlusFloatSetting.create("Roof Obsidian Opacity",
            -1, 15, 1,
                    "Sets the opacity of the transparent obsidian roof tiles. Does not update tiles already mapped - you need to remap them. \\n " +
                    "Change this to -1 to completely hide roof obsidian.",
            (v) -> markChunksDirtyInWriteDistance(),
            5, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting worldMapMinZoomSetting = XaeroPlusFloatSetting.create("Min WorldMap Zoom",
            0, 0.625f, 0.01f,
                    "Minimum WorldMap Zoom Setting. This is 10x what you see on the WorldMap.",
            0.1f, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting skipWorldRenderSetting = XaeroPlusBooleanSetting.create("Skip Background Render",
                    "Skip MC world render while in a Xaero GUI. Having this on can cause issues with travel mods while you're in a Xaero GUI like the WorldMap.",
            false, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting newChunksEnabledSetting = XaeroPlusBooleanSetting.create("NewChunks Highlighting",
                    "Highlights NewChunks on the Minimap and WorldMap.",
            (b) -> ModuleManager.getModule(NewChunks.class).setEnabled(b),
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting newChunksSaveLoadToDisk = XaeroPlusBooleanSetting.create("Save/Load NewChunks to Disk",
            "Saves and loads NewChunk data to disk for each world and dimension. Requires NewChunk Highlighting to be enabled.",
            (b) -> ModuleManager.getModule(NewChunks.class).setSaveLoad(b),
            false, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting newChunksSeenResetTime = XaeroPlusFloatSetting.create("Reset Seen Chunks Time",
            0, 1000f, 10f,
            "If we load a chunk in our NewChunk list again, reset it to an old chunk after this time period in seconds."
                    + " \\n 0 = never reset chunks",
            0f, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting newChunksDimensionReload = XaeroPlusBooleanSetting.create("Reload NewChunks",
            "If Save/Load disabled, reload all NewChunks on world or dimension change.",
            false, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting newChunksAlphaSetting = XaeroPlusFloatSetting.create("New Chunks Opacity",
            10f, 255f, 10f,
                    "Changes the color opacity of NewChunks.",
            (b) -> ModuleManager.getModule(NewChunks.class).setAlpha(b),
            100, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting wdlEnabledSetting = XaeroPlusBooleanSetting.create("WDL Highlight",
                    "Highlights chunks WDL mod has downloaded on the Minimap and WorldMap.",
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusFloatSetting wdlAlphaSetting = XaeroPlusFloatSetting.create("WDL Opacity",
            10f, 255f, 10f,
                    "Changes the color opacity of WDL chunks.",
            WDLHelper::setAlpha,
            100, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting owAutoWaypointDimension = XaeroPlusBooleanSetting.create("Prefer Overworld Waypoints",
                    "Prefer creating and viewing Overworld waypoints when in the nether.",
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting showWaypointDistances = XaeroPlusBooleanSetting.create("Show Waypoint Distances",
            "Display the distance to every waypoint in the full waypoint menu.",
            true, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusBooleanSetting showRenderDistanceSetting = XaeroPlusBooleanSetting.create("Show Render Distance",
            "Show server side render distance (actually just another setting)",
            false, SettingLocation.MINIMAP_OVERLAYS);
    public static final XaeroPlusFloatSetting assumedServerRenderDistanceSetting = XaeroPlusFloatSetting.create("Server Render Distance",
            1f, 32f, 1f,
            "view-distance of the server",
            4f, SettingLocation.MINIMAP_OVERLAYS); // 2b2t
    public static final XaeroPlusBooleanSetting nullOverworldDimensionFolder = XaeroPlusBooleanSetting.create("null OW Dim Dir",
            "Sets whether the overworld WorldMap directory is in DIM0 or null (null is the original Xaero name)"
                    + " \\n MC must be restarted for changes to take effect.",
            false, SettingLocation.WORLD_MAP_MAIN);
    public static final XaeroPlusEnumSetting<DataFolderResolutionMode> dataFolderResolutionMode = XaeroPlusEnumSetting.create("Data Dir Mode",
            "Sets how the WorldMap and Waypoints data folders are resolved."
                    + " \\n MC must be restarted for changes to take effect."
                    + " \\n IP = Server IP (Xaero Default). Example: .minecraft/XaeroWorldMap/Multiplayer_connect.2b2t.org"
                    + " \\n SERVER_NAME = MC Server Entry Name (XaeroPlus Default). Example: .minecraft/XaeroWorldMap/Multiplayer_2b2t"
                    + " \\n BASE_DOMAIN = Base Server Domain Name. Example: .minecraft/XaeroWorldMap/Multiplayer_2b2t.org",
            DataFolderResolutionMode.values(), DataFolderResolutionMode.SERVER_NAME, SettingLocation.WORLD_MAP_MAIN);

    public enum DataFolderResolutionMode {
        IP, SERVER_NAME, BASE_DOMAIN;
    }
}
