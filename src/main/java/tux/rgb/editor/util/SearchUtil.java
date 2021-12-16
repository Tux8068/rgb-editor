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
        List<File> allFiles = new ArrayList<>();
        File[] containing = file.listFiles();
        assert containing != null;
        for (File value : containing) {
            if (value.isFile()) allFiles.add(value);
            else if (value.isDirectory()) allFiles.addAll(getContaining(value));
        }
        return allFiles;
    }
}