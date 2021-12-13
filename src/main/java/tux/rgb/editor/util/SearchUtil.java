package tux.rgb.editor.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchUtil {

    /**
     * @author Kami147
     * @since 10/12/21
     */

    public static List<File> getContaining(File file){
        List<File> allFiles = new ArrayList<File>();
        File[] containing = file.listFiles();
        for (int i = 0; i < containing.length; i++) {
            if (containing[i].isFile()) allFiles.add(containing[i]);
            else if (containing[i].isDirectory()) for(File f : getContaining(containing[i])) allFiles.add(f);
        }
        return allFiles;
    }
}