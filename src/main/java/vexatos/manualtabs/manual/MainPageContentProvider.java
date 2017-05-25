package vexatos.manualtabs.manual;

import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.manual.ContentProvider;

import javax.annotation.Nullable;
import java.util.regex.Pattern;

/**
 * @author Vexatos
 */
public class MainPageContentProvider implements ContentProvider {
    private static final Pattern indexPattern = Pattern.compile("[a-zA-Z_]+/index.md");
    private final String mainPath;

    public MainPageContentProvider(String mainPath) {
        this.mainPath = mainPath;
    }

    private boolean sentinel = false; // Preventing recursion

    @Nullable
    @Override
    public Iterable<String> getContent(String path) {
        if (sentinel) {
            return null;
        }
        path = path.startsWith("/") ? path.substring(1) : path;
        if (indexPattern.matcher(path).matches()) {
            sentinel = true;
            final Iterable<String> content = ManualAPI.contentFor(mainPath);
            sentinel = false;
            return content;
        }
        return null;
    }
}
