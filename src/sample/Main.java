package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ToggleButton");

        // добавление на сцены на форму
        VBox root = new VBox(); // Основное


        //--------------------------------------------------------------------------- ToggleButton

        HBox hBox = new HBox(10); // вертикальное
        hBox.setAlignment(Pos.CENTER);

        // наша группа
        ToggleGroup group = new ToggleGroup();

        // наши кнопки с текстом
        ToggleButton toggleButton1 = new ToggleButton("RED");
        ToggleButton toggleButton2 = new ToggleButton("GREEN");
        ToggleButton toggleButton3 = new ToggleButton("BLUE");

        // перекрашиваем стиль кнопок
        toggleButton1.setStyle("-fx-base: RED");
        toggleButton2.setStyle("-fx-base: GREEN");
        toggleButton3.setStyle("-fx-base: BLUE");

        toggleButton3.setSelected(true); // выбираем кнопку

        // информация по выбранной кнопке
        toggleButton1.setUserData(Color.RED);
        toggleButton2.setUserData(Color.GREEN);
        toggleButton3.setUserData(Color.BLUE);

        // добавляем наши кнопки в группу
        toggleButton1.setToggleGroup(group);
        toggleButton2.setToggleGroup(group);
        toggleButton3.setToggleGroup(group);

        // Создаем квадрат
        Rectangle rectangle = new Rectangle(300, 300); // размеры
        rectangle.setFill(Color.WHITE); // заливка основного фона
        rectangle.setStroke(Color.DARKGOLDENROD); // и границы
        rectangle.setStrokeWidth(2); // толщина
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);

        // добавляем наши кнопки на горизонтальную панель
        hBox.getChildren().addAll(toggleButton1, toggleButton2, toggleButton3);

        // обработчик событий
        group.selectedToggleProperty().addListener(observable -> {
           if(group.getSelectedToggle()!=null){
               rectangle.setFill((Color)group.getSelectedToggle().getUserData()); // перекрашиваем
           }else {
               rectangle.setFill(Color.WHITE);
           }
        });

        BorderPane borderPane = new BorderPane(); // Основное
        borderPane.setTop(hBox);
        borderPane.setCenter(rectangle);
        root.getChildren().addAll(borderPane);

        //--------------------------------------------------------------------------- CheckBox

        HBox hBoxChB = new HBox(10); // вертикальное
        hBoxChB.setAlignment(Pos.CENTER);
        HBox hBoxRec = new HBox(10); // вертикальное
        hBoxRec.setAlignment(Pos.CENTER);

        // создание CheckBox массива
        CheckBox[] checkBoxes = new CheckBox[]{
                new CheckBox("RED"),
                new CheckBox("GREEN"),
                new CheckBox("BLUE"),
        };

        // создание нескольких квадратов
        Rectangle[] rectangles = new Rectangle[]{
                new Rectangle(100, 100, Color.RED),
                new Rectangle(100, 100, Color.GREEN),
                new Rectangle(100, 100, Color.BLUE),
        };

        for (int i=0; i<3; i++){
            CheckBox newCheckBox = checkBoxes[i]; // выбираем определённый CheckBox  и квадрат
            Rectangle newRectangle = rectangles[i];

            hBoxChB.getChildren().add(newCheckBox); // добавляем  CheckBox на форму

            newCheckBox.selectedProperty().addListener(observable ->{ // проверяем нажата или нет? И действие на нажатие
                if(newCheckBox.isSelected()){
                    hBoxRec.getChildren().add(newRectangle); // добавть в hBoxRec
                }else{
                    hBoxRec.getChildren().remove(newRectangle); // удалить
                }
            });
        }

        // добавляем на форму основные элементы
        BorderPane borderPane2 = new BorderPane(); // Основное 2
        borderPane2.setTop(hBoxChB);
        borderPane2.setCenter(hBoxRec);
        root.getChildren().addAll(borderPane2);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);  // размер формы и сцена
        primaryStage.show(); // отобразить
    }

    public static void main(String[] args) {
        launch(args);
    }
}