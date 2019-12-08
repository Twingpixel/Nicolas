package com.someguyssoftware.gottschcore.mod;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.someguyssoftware.gottschcore.config.IConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

/**
 * 
 * @author Mark Gottschling on Nov 14, 2019
 *
 */
public abstract class AbstractConfig implements IConfig {
    protected static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    protected static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static Mod MOD;
    public static Logging LOGGING;

	static {
    	MOD = new Mod(COMMON_BUILDER);
    	LOGGING = new Logging(COMMON_BUILDER);
    }
	
	/**
	 * 
	 * @author Mark Gottschling on Nov 16, 2019
	 *
	 */
    public static class Logging {
    	public ConfigValue<String> level;
    	public ConfigValue<String> folder;
    	public ConfigValue<String> size;
    	public ConfigValue<String> filename;
    	
    	Logging(final ForgeConfigSpec.Builder builder) {
    		builder
    			.comment("Logging properties")
    			.push(LOGGING_CATEGORY);

    		level = builder.comment("The logging level. Set to 'off' to disable logging.", "Values = [trace|debug|info|warn|error|off]").define("level", DEFAULT_LOGGER_LEVEL);
    		folder = builder.comment("The directory where the logs should be stored.", "This is relative to the Minecraft mods folder").define("folder", DEFAULT_LOGGER_FOLDER);
       		size = builder.comment("The size a log file can be before rolling over to a new file.").define("size", DEFAULT_LOGGER_SIZE);
       		filename = builder.comment("The base filename of the  log file.").define("filename", DEFAULT_LOGGER_FILENAME);
       		builder.pop();
    	}
    }
    
    /**
     * 
     * @author Mark Gottschling on Nov 16, 2019
     *
     */
    public static class Mod {
    	public ConfigValue<String> folder;
    	@Deprecated
    	public ForgeConfigSpec.BooleanValue enabled;
    	public ForgeConfigSpec.BooleanValue enableVersionChecker;
    	public ConfigValue<String> latestVersion;
    	public ForgeConfigSpec.BooleanValue latestVersionReminder;
    	
	    public Mod(final ForgeConfigSpec.Builder builder) {
			builder
			.comment("General mod properties")
			.push(MOD_CATEGORY);    	
			
		    folder = builder.comment("The relative path to the 'mods' folder.").define("folder", DEFAULT_MODS_FOLDER);
		    enabled = builder.comment("").define("enabled", true);
		    enableVersionChecker = builder.comment("").define("enableVersionChecker", true);
		    latestVersion = builder.comment("").define("latestVersion", "");
		    latestVersionReminder = builder.comment("").define("latestVersionReminder", true);
		    
       		builder.pop();
	    }	 
    }
    
	/**
	 * 
	 * @param spec
	 * @param path
	 */
    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
