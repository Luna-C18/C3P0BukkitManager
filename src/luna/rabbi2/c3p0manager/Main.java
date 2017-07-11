package luna.rabbi2.c3p0manager;

import luna.rabbi2.c3p0manager.obj.C3P0Manager;
import luna.rabbi2.c3p0manager.uti.ClassLoader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 *
 * Created by Luna-C18 on 2017/7/11.
 */
public class Main extends JavaPlugin {

    private C3P0Manager c3P0Manager;

    @Override
    public void onEnable() {

        this.saveResource("lib"+ File.separator+"c3p0-0.9.5.2.jar", false);
        this.saveResource("lib"+File.separator+"mchange-commons-java-0.2.11.jar", false);

        // ClassLoader
        if(!ClassLoader.loadClass(this, "c3p0-0.9.5.2.jar")) {
            this.getLogger().warning("c3p0-0.9.5.2.jar 不存在");
            this.getServer().shutdown();
        }
        if(!ClassLoader.loadClass(this, "mchange-commons-java-0.2.11.jar")) {
            this.getLogger().warning("mchange-commons-java-0.2.11.jar 不存在");
            this.getServer().shutdown();
        }

        FileConfiguration config = this.getConfig();
        this.c3P0Manager = C3P0Manager.getC3P0Util(
                config.getString("settings.address"),
                config.getString("settings.port"),
                config.getString("settings.database"),
                config.getString("settings.user"),
                config.getString("settings.pword"),
                config.getString("settings.ssl"),
                config.getInt("c3p0.min"),
                config.getInt("c3p0.add"),
                config.getInt("c3p0.max")
                );
    }

    public C3P0Manager getC3P0Manager() {
        return c3P0Manager;
    }

    @Override
    public void onDisable() {
        this.c3P0Manager.close();
    }
}
