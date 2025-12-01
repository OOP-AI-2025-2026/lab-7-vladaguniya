package ua.opnu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingList extends Application {

    private ObservableList<Student> students;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Список студентів (Lambda Edition)");
        students = populateList();

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);

        final ListView<Student> listView = new ListView<>(students);
        listView.setPrefSize(450, 250);

        final HBox hbox = setButtons();
        vbox.getChildren().addAll(listView, hbox);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ObservableList<Student> populateList() {
        return FXCollections.observableArrayList(
                new Student("Ваулін", "Данило", "AI244", new int[]{90, 85, 100}),
                new Student("Лампіга", "Владислав", "AI244", new int[]{60, 59, 70}),
                new Student("Демешко", "Микита", "AI244", new int[]{40, 90, 80}),
                new Student("Гунія", "Владислава", "AI244", new int[]{95, 98, 99}),
                new Student("Штумбенко", "Роман", "AI245", new int[]{96, 91, 100})
        );
    }

    private HBox setButtons() {
        final Button sortByNameButton = new Button("Ім'я");
        final Button sortByLastNameButton = new Button("Прізвище");
        final Button sortByMarkButton = new Button("Оцінка");

        HBox.setHgrow(sortByNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByLastNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByMarkButton, Priority.ALWAYS);
        sortByNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByLastNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByMarkButton.setMaxWidth(Double.MAX_VALUE);

        final boolean[] nameAsc = {true};
        final boolean[] lastNameAsc = {true};
        final boolean[] markAsc = {true};

        sortByNameButton.setOnAction(event -> {
            students.sort((s1, s2) -> {
                if (nameAsc[0]) return s1.getFirstName().compareTo(s2.getFirstName());
                else return s2.getFirstName().compareTo(s1.getFirstName());
            });
            nameAsc[0] = !nameAsc[0];
        });

        sortByLastNameButton.setOnAction(event -> {
            students.sort((s1, s2) -> {
                if (lastNameAsc[0]) return s1.getLastName().compareTo(s2.getLastName());
                else return s2.getLastName().compareTo(s1.getLastName());
            });
            lastNameAsc[0] = !lastNameAsc[0];
        });

        sortByMarkButton.setOnAction(event -> {
            students.sort((s1, s2) -> {
                if (markAsc[0]) return Double.compare(s1.getAverageMark(), s2.getAverageMark());
                else return Double.compare(s2.getAverageMark(), s1.getAverageMark());
            });
            markAsc[0] = !markAsc[0];
        });

        HBox hb = new HBox(5);
        hb.getChildren().addAll(sortByNameButton, sortByLastNameButton, sortByMarkButton);
        hb.setAlignment(Pos.CENTER);

        return hb;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
