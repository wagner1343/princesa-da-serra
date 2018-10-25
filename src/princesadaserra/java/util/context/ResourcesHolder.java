package princesadaserra.java.util.context;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourcesHolder {
    private static Locale defaultLocale = new Locale("en", "US");
    private static ResourceBundle resourceBundle;

    public static ResourceBundle getResourceBundle(){
        if(resourceBundle == null)
            resourceBundle = ResourceBundle.getBundle("locale.strings", defaultLocale);

        return resourceBundle;
    }
}
