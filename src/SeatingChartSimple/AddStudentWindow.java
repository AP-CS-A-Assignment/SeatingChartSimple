package SeatingChartSimple;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AddStudentWindow extends Stage {
    private Scene scene;
        private VBox vBox;
            private Label info;
            private Label example;
            private TextArea inputField;
                private Button submit;

    private double height = 300;
    private double width = 400;

    public AddStudentWindow(SeatingMain main) {
        super();
        setupPopup();

        scene = new Scene(new Group());
        setScene(scene);

            vBox = new VBox();
//            vBox.setMinSize(width, height);
            vBox.setPadding(new Insets(10));
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.setSpacing(5);
            scene.setRoot(vBox);

                info = new Label("Add one student per line in the format");
                info.setFont(Font.font("Arial", 20));
                info.setWrapText(true);
                info.setTextAlignment(TextAlignment.CENTER);
                vBox.getChildren().add(info);

                example = new Label("FirstName LastName");
                example.setFont(Font.font("Times New Roman", 20));
                example.setWrapText(true);
                example.setTextAlignment(TextAlignment.CENTER);
                vBox.getChildren().add(example);

                inputField = new TextArea();
                inputField.setFont(Font.font("Arial", 25));
                inputField.setFocusTraversable(false);
                inputField.setPromptText("Enter students here");
                vBox.getChildren().add(inputField);

                submit = new Button("Done");
                submit.setFont(Font.font("Arial", 15));
                vBox.getChildren().add(submit);
                submit.setOnMouseClicked(event -> {
                    main.fillSeats(inputField.getText());
                    this.close();
                });
    }

    private void setupPopup() {
        setWidth(width);
        setHeight(height);
        setMinHeight(height);
        setMinWidth(width);
        centerOnScreen();
        setAlwaysOnTop(true);
        setTitle("Add Students");
        show();
    }
}
