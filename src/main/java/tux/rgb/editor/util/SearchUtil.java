package tux.rgb.editor.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchUtil {

    public static List<File> search(Path dir) throws IOException {
        return Files.walk(Paths.get(String.valueOf(dir)))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static List<File> getContaining(File file){
        System.out.println(file);
        List<File> allFiles = new ArrayList<File>();
        File[] containing = file.listFiles();
        for (int i = 0; i < containing.length; i++) { //Thanks stack overflow :3 (https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder)
            if (containing[i].isFile()) {
               // System.out.println("File " + containing[i].getName());
                allFiles.add(containing[i]);
            } else if (containing[i].isDirectory()) {
               // System.out.println("Directory " + containing[i].getName());
                for(File f : getContaining(containing[i])) allFiles.add(f);
            }
        }
        return allFiles;
    }
}
