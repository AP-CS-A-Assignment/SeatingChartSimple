package SeatingChartSimple;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;

public final class InOut {
    private static FileChooser fileChooser = new FileChooser();
    private static String ext = ".seats";
    public static void export(LinkedList<String[]> names, Stage stage) throws IOException {
        fileChooser.setInitialFileName("*" + ext);
        File saveLocation = fileChooser.showSaveDialog(stage);
        if (saveLocation != null){
            String fileName = saveLocation.getAbsolutePath();
            if (fileName.lastIndexOf(".") > 0)    {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
            saveLocation = new File(fileName + ext);

            FileOutputStream out = new FileOutputStream(saveLocation);
            String total = "";
            for (String[] namePair : names) {
                total += namePair[0] + " " + namePair[1] + "\n";
            }
            out.write(total.getBytes());
            out.close();
        }
    }

    public static String importNames(Stage stage) throws IOException {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SeatingChart", "*" + ext));
        File openFile = fileChooser.showOpenDialog(stage);
        if (openFile != null)   {
            FileInputStream in = new FileInputStream(openFile);
            byte[] b = new byte[(int) openFile.length()];
            in.read(b);
            return new String(b);
        }
        return "";
    }
}
