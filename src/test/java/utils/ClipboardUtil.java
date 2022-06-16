package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.nio.file.Path;

public class ClipboardUtil {

    public static void setValueToClipBoard(Path value) {
        StringSelection stringSelection = new StringSelection(value.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
}
