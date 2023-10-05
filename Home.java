package PhoneBook;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home extends Application {
    private Button bt1, bt2, bt3, bt4, bt5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home Section");

        Label l1 = new Label("Phone Book");
        l1.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        bt1 = new Button("Entry");
        bt2 = new Button("Search");
        bt3 = new Button("Edit");
        bt4 = new Button("Delete");
        bt5 = new Button("Exit");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.getChildren().addAll(l1, bt1, bt2, bt3, bt4, bt5);

        Scene scene = new Scene(vbox, 350, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
        bt1.setOnAction((ActionEvent event) -> {
            new EntryData().start(new Stage());
            //System.out.println("Entry");
            primaryStage.close();
        });

        bt2.setOnAction((ActionEvent event) -> {
            new SearchData().start(new Stage());
            //System.out.println("Search");
            primaryStage.close();
        });

        bt3.setOnAction((ActionEvent event) -> {
            new SearchDataForEdit().start(new Stage());
            //System.out.println("Edit");
            primaryStage.close();
        });

        bt4.setOnAction((ActionEvent event) -> {
            new DeleteContact().start(new Stage());
            //System.out.println("Delete");
            primaryStage.close();
        });

        bt5.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
    }
}
    /*}

    
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(150);
        button.setStyle("-fx-font-size: 16px;");
        return button;
    }

    private void openWindow(Application window) {
        Stage stage = new Stage();
        window.start(stage);
        ((Stage) bt1.getScene().getWindow()).close();
    }
}*/