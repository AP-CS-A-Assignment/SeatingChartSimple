package SeatingChartSimple;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;

/**
 * Controls the importing and exporting of seating charts
 */
public final class InOut {
    /**
     * Allows for the choosing of a file to load or where to save a file
     */
    private static FileChooser fileChooser = new FileChooser();
    /**
     * The extensions of the files
     */
    private static String ext = ".seats";

    /**
     * Takes a list of names and saves them to a file
     * @param names List of names to save
     * @param stage the stage that called the method
     * @throws IOException
     */
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

    /**
     * Imports a seating chart from a file
     * @param stage the stage that called the method
     * @return a newLine seperated string of the names
     * @throws IOException
     */
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
