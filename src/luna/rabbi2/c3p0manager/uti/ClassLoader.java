package luna.rabbi2.c3p0manager.uti;

import luna.rabbi2.c3p0manager.Main;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * Created by Luna-C18 on 2017/7/1.
 */
public class ClassLoader {
    public static boolean loadClass(Main plugin, String jarName) {
        File file = new File(plugin.getDataFolder(), "c3p0s" +File.separator+ jarName);
        if(file.exists()) {
            try {
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                if(!method.isAccessible()) {
                    method.setAccessible(true);
                }
                URLClassLoader classLoader = (URLClassLoader) java.lang.ClassLoader.getSystemClassLoader();
                URL url = file.toURI().toURL();
                method.invoke(classLoader, url);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
}
