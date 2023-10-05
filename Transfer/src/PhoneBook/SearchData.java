package PhoneBook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchData extends Application {
    private Label l1, l2;
    private TextField tf1;
    private Button bt1, bt2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Contact");

        l1 = new Label("Search Contact");
        l2 = new Label("Enter Name");

        tf1 = new TextField();

        bt1 = new Button("Search Contact");
        bt2 = new Button("Back");
        l1.setStyle("-fx-font-size: 25px;");
        l2.setStyle("-fx-font-size: 15px;");
        tf1.setStyle("-fx-font-size: 15px;");
        bt1.setStyle("-fx-font-size: 15px;");
        bt2.setStyle("-fx-font-size: 15px;");

        bt1.setOnAction(event -> {
            String name = tf1.getText();
            try {
                String Url = "jdbc:mysql://localhost:3306/PhoneManagement";
                String User = "root";
                String Password = "joshua123";

                try (Connection connection = DriverManager.getConnection(Url, User, Password)) {
                    String q = "SELECT name FROM add_contact WHERE name=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(q);
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) 
                    {
                        String name1 = resultSet.getString("name");
                        primaryStage.close();
                            //Home home = new Home();
                            //home.start(new Stage());
                           SearchDataTable searchDataTable= new SearchDataTable(name1);
                           searchDataTable.start(new Stage());
                    } 
                    else 
                    
                    {
                        StackPane secondaryLayout = new StackPane();
                        Label secondaryLabel = new Label("Your Contact is not found");
                        secondaryLayout.getChildren().add(secondaryLabel);
                        Scene secondScene = new Scene(secondaryLayout, 230, 100);
                        Stage newWindow = new Stage();
                        newWindow.setTitle("Contact Not Found");
                        newWindow.setScene(secondScene);
                        newWindow.show();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        bt2.setOnAction(event -> {
            primaryStage.close();
            new Home().start(new Stage());
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(l1, 0, 0, 2, 1);
        gridPane.add(l2, 0, 1);
        gridPane.add(tf1, 1, 1);
        gridPane.add(bt1, 0, 2);
        gridPane.add(bt2, 1, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 470, 180);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
