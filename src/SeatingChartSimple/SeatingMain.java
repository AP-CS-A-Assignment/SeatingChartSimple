package SeatingChartSimple;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class SeatingMain extends Application {
    private Stage stage;
    private Scene scene;
    private StackPane root;
        private Rectangle background;
            private Image bkgd;
                private ImagePattern pattern;
        private VBox vbox;
            private MenuBar menuBar;
                private Menu fileMenu;
                    private MenuItem exportItem;
                    private MenuItem importItem;
                private Menu studentsMenu;
                    private MenuItem addStudentsItem;
                    private MenuItem swapItem;
                    private MenuItem removeItem;
            private GridPane gridPane;
                private Student[][] students;
                private LinkedList<String[]> nameList;

    private int arrSize = 5;

    @Override
    public void start(Stage stage) throws Exception {
        nameList = new LinkedList<>();

        this.stage = stage;
        setupStage();

        scene = new Scene(new Group());
        System.out.println(scene);
        stage.setScene(scene);

        root = new StackPane();
        root.setMinWidth(scene.getWidth());
        root.setMinHeight(scene.getHeight());
        scene.setRoot(root);

            background = new Rectangle(0, 0, scene.getWidth(), scene.getHeight());
            root.getChildren().add(background);

                try {
                    bkgd = new Image(getClass().getClassLoader().getResourceAsStream("SeatingChartSimple/wood.jpg"));
                    pattern = new ImagePattern(bkgd, 0, 0, 200, 100, false);
                    background.setFill(pattern);
                } catch (Exception e)   {
                    System.out.println("whoops");
                }


            vbox = new VBox();
            vbox.setMinSize(root.getMinWidth(), root.getMinHeight());
            vbox.setSpacing(10);
            root.getChildren().add(vbox);

                menuBar = new MenuBar();
                vbox.getChildren().add(menuBar);

                    fileMenu = new Menu("File");
                    menuBar.getMenus().add(fileMenu);

                        exportItem = new MenuItem("Export");
                        fileMenu.getItems().add(exportItem);

                            exportItem.setOnAction(event -> {
                                try {
                                    InOut.export(nameList, stage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        importItem = new MenuItem("Import");
                        fileMenu.getItems().add(importItem);

                            importItem.setOnAction(event -> {
                                clear();
                                try {
                                    fillSeats(InOut.importNames(stage));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                    studentsMenu = new Menu("Students");
                    menuBar.getMenus().add(studentsMenu);

                        addStudentsItem = new MenuItem("Add Students");
                        studentsMenu.getItems().add(addStudentsItem);

                            addStudentsItem.setOnAction(event -> {
                                System.out.println("addStudent");
                                new AddStudentWindow(this);
                            });

                        swapItem = new MenuItem("Swap Students");
                        studentsMenu.getItems().add(swapItem);

                            swapItem.setOnAction(event -> {
                                if(!Student.isSwap())    {
                                    Student.setSwap(true);
                                    swapItem.setText("End Swap");
                                } else {
                                    Student.setSwap(false);
                                    swapItem.setText("Swap Students");
                                }
                            });

                        removeItem = new MenuItem("Remove Students");
                        studentsMenu.getItems().add(removeItem);

                            removeItem.setOnAction(event -> {
                                if(!Student.isSwap())   {
                                    if (!Student.isRemove()) {
                                        Student.setRemove(true);
                                        removeItem.setText("End Remove");
                                    } else {
                                        Student.setRemove(false);
                                        removeItem.setText("Remove Students");
                                    }
                                }
                            });

                gridPane = new GridPane();
    //            gridPane.setMinWidth(root.getMinWidth());
    //            gridPane.setMinHeight(root.getMinHeight());
    //            gridPane.setPrefWidth(root.getMinWidth());
    //            gridPane.setPrefHeight(root.getMinHeight());
                gridPane.setVgap(20);
                gridPane.setHgap(20);
                gridPane.setPadding(new Insets(10));
                VBox.setVgrow(gridPane, Priority.ALWAYS);
                vbox.getChildren().add(gridPane);


                    for (int i = 0; i < arrSize; i++) {
                        ColumnConstraints cc = new ColumnConstraints();
                        cc.setFillWidth(true);
                        cc.setHgrow(Priority.ALWAYS);
                        gridPane.getColumnConstraints().add(cc);

                        RowConstraints rc = new RowConstraints();
                        rc.setFillHeight(true);
                        rc.setVgrow(Priority.ALWAYS);
                        gridPane.getRowConstraints().add(rc);
                    }

                    Image desk = null;
                    try {
                        desk = new Image(getClass().getClassLoader().getResourceAsStream("SeatingChartSimple/desk.png"));
                    } catch (Exception e)   {
                        System.out.println("gooly gee");
                    }
                    students = new Student[arrSize][arrSize];
                    for (int r = 0; r < arrSize; r++) {
                        for (int c = 0; c < arrSize; c++) {
                            Student a = new Student((scene.getWidth() - 100) / arrSize, desk);
                            students[r][c] = a;
                            gridPane.add(a, c, r);
                            a.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                            GridPane.setFillWidth(a, true);
                            GridPane.setFillHeight(a, true);
                        }
                    }
    }

    public void fillSeats(String studentsRaw) {
        String[] names = studentsRaw.split("\n");
        for (String name : names) {
            String trimmedName = name.trim();
            String[] parts = trimmedName.split(" ");
            System.out.println("part1 " + parts[0]);
            if(parts.length > 0 && nameList.size() < arrSize * arrSize) {
                String[] fullName = {parts[0], (parts.length <= 1) ? " " : parts[1]};
                nameList.add(0, fullName);
            }
        }
        for (String[] strings : nameList) {
            System.out.println("--" + strings[0]);
        }
        int cnt = 0;
        for (int col = 0; col < students.length && cnt < nameList.size(); col++) {
            for (int row = 0; row < students[0].length && cnt < nameList.size(); row++) {
                students[row][col].setStudent(nameList.get(cnt)[0], nameList.get(cnt++)[1]);
            }
        }
    }

    private void clear()    {
        nameList.clear();
        for (Student[] studentR : students) {
            for (Student student : studentR) {
                student.setStudent("");
            }
        }
    }

    private void setupStage()   {
        int size = 800;
        stage.setWidth(size);
        stage.setHeight(size);
        stage.setTitle("Seating Chart Simple");
        try {
            stage.getIcons().add(new Image(getClass().getClassLoader().getResource("SeatingChartSimple/DracoIncLogoIconV2.png").toExternalForm()));
        } catch (Exception e)   {
            System.out.println("oops");
        }
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        SeatingMain.launch(args);
    }
}
