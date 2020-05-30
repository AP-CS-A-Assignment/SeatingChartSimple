package SeatingChartSimple;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents a student and holds its information
 */
public class Student extends AnchorPane {

    /**
     * The first student selected for a swap
     */
    private static Student student1 = null;
    /**
     * Flag for whether a swap is going on
     */
    private static boolean swap = false;
    /**
     *  flag for whether a remove is going on
     */
    private static boolean remove = false;

    /**
     * Rectangel that represents the desk
     */
    private Rectangle desk;
    /**
     * Represents the head of the student at the desk
     */
    private Ellipse head;
    /**
     * The name of the student
     */
    private Label name;

    /**
     * Sets up the node graph, and sets what happends on a click depending on the flag that it set
     * @param width the width of the student
     * @param deskImage the image for all the desks
     */
    public Student(double width, Image deskImage) {
        super();
        double height = width * .5;
        desk = new Rectangle(width, height);
        desk.setFill(new ImagePattern(deskImage));
        getChildren().add(desk);

        head = new Ellipse(width / 2, height * 1.2, width * .3, height * .4);
        head.setFill(Color.SADDLEBROWN);
        getChildren().add(head);

        name = new Label();
        name.setAlignment(Pos.TOP_CENTER);
        name.setFont(Font.font("Arial", 20));
        AnchorPane.setTopAnchor(name, 0.0);
        AnchorPane.setLeftAnchor(name, 0.0);
        AnchorPane.setRightAnchor(name, 0.0);
        getChildren().add(name);

        setOnMouseClicked(event -> {
            if(remove)  {
                name.setText(" ");
            }
            if (swap){
                if (student1 == null) {
                    student1 = this;
                    student1.border(true);

                } else {
                    border(true);
                    String student2Name = name.getText();
                    setStudent(student1.getName());
                    student1.setStudent(student2Name);
                    student1.border(false);
                    border(false);
                    student1 = null;

                }
            }
        });
    }

    /**
     * Sets the name of the student to the first name and the Initail of the last name
     * @param first The First name to set for the student
     * @param last The last name to set for the Student
     */
    public void setStudent(String first, String last) {
        name.setText(first + " " + ((last.length() > 0) ? last.charAt(0) + "." : ""));
    }

    /**
     * Sets the students name
     * @param nameString Nmae to the student to
     */
    public void setStudent(String nameString) {
        name.setText(nameString);
    }

    /**
     * @return the Name of the Student
     */
    public String getName() {
        return name.getText();
    }

    /**
     * Sets the swap flag
     * @param b what to set the swap flag to
     */
    public static void setSwap(boolean b)  {
        swap = b;
    }
    /**
     * Gets whether the swap flag is set
     * @return the stage of the Swap flag
     */
    public static boolean isSwap() {
        return swap;
    }

    /**
     * @return The remove flag
     */
    public static boolean isRemove() {
        return remove;
    }

    /**
     * Sets the remmove flag
     * @param remove what to set the flag to
     */
    public static void setRemove(boolean remove) {
        Student.remove = remove;
    }

    /**
     * Sets the fill based on the parameter
     * @param b if true the head is orange, if false the head is Saddlebrown
     */
    private void border(boolean b)  {
        if (b)  {
//            setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            head.setFill(Color.ORANGE);
        } else {
//            setBorder(Border.EMPTY);
            head.setFill(Color.SADDLEBROWN);
        }
    }

}
