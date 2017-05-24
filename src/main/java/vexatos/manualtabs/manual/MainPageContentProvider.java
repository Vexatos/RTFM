package vexatos.manualtabs.manual;

import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.manual.ContentProvider;

import javax.annotation.Nullable;

/**
 * @author Vexatos
 */
public class MainPageContentProvider implements ContentProvider {

	private final String mainPath;

	public MainPageContentProvider(String mainPath) {
		this.mainPath = mainPath;
	}

	private boolean sentinel = false; // Preventing recursion

	@Nullable
	@Override
	public Iterable<String> getContent(String path) {
		if(sentinel) {
			return null;
		}
		path = path.startsWith("/") ? path.substring(1) : path;
		if(path.matches("[a-zA-Z_]+/index.md")) {
			sentinel = true;
			final Iterable<String> content = ManualAPI.contentFor(mainPath);
			sentinel = false;
			return content;
		}
		return null;
	}
}
