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

public class Student extends AnchorPane {

    private static Student student1 = null;
    private static boolean swap = false;
    private static boolean remove = false;

    private Rectangle desk;
    private Ellipse head;
    private Label name;


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

    public void setStudent(String first, String last) {
        name.setText(first + " " + ((last.length() > 0) ? last.charAt(0) + "." : ""));
    }

    public void setStudent(String nameString) {
        name.setText(nameString);
    }

    public String getName() {
        return name.getText();
    }

    public static void setSwap(boolean b)  {
        swap = b;
    }

    public static boolean isSwap() {
        return swap;
    }

    public static boolean isRemove() {
        return remove;
    }

    public static void setRemove(boolean remove) {
        Student.remove = remove;
    }

    private void border(boolean b)  {
        if (b)  {
//            setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            head.setFill(Color.ORANGE);
        } else {
//            setBorder(Border.EMPTY);
            head.setFill(Color.SADDLEBROWN);
        }
    }

    public void test()  {
        System.out.println();
    }
}
