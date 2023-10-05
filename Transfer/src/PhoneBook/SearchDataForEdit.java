package PhoneBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchDataForEdit extends Application {

    Label l1, l2;
    TextField tf1;
    Button bt1, bt2;
    Font f, f1;
    VBox p1, p2;
    int id = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Name for Edit");
        primaryStage.setX(450);
        primaryStage.setY(50);
        primaryStage.setWidth(470);
        primaryStage.setHeight(180);

        f = new Font("Arial", 25);
        f1 = new Font("Arial", 15);

        l1 = new Label("Search Name for Edit");
        l2 = new Label("Enter Name");

        tf1 = new TextField();

        bt1 = new Button("Edit Contact");
        bt2 = new Button("Back");

        l1.setStyle("-fx-font-weight: bold; -fx-font-size: 25;");
        l2.setStyle("-fx-font-weight: bold; -fx-font-size: 15;");
        tf1.setStyle("-fx-font-size: 15;");
        bt1.setStyle("-fx-font-size: 15;");
        bt2.setStyle("-fx-font-size: 15;");

        p1 = new VBox();
        p1.getChildren().add(l1);

        p2 = new VBox(10);
        p2.getChildren().addAll(l2, tf1, bt1, bt2);

        BorderPane root = new BorderPane();
        root.setTop(p1);
        root.setCenter(p2);

        bt1.setOnAction((ActionEvent event) -> {
            String name = tf1.getText();
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load your JDBC driver
                Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonemanagement", "root", "joshua123");
                String q = "select name from add_contact where name='" + name + "'";
                Statement stm = obj.createStatement();
                ResultSet rest = stm.executeQuery(q);
                if (rest.next()) {
                    String name1 = rest.getString("name");
                    primaryStage.hide();
                    new SearchDataTableForEdit(name1).start(new Stage());
                } else {
                    id = 0;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Contact Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Contact is not found");
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        bt2.setOnAction((ActionEvent event) -> {
            primaryStage.hide();
            new Home().start(new Stage());
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
