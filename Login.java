package PhoneBook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends Application {
    private TextField tf;
    private PasswordField pf;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login PhoneBook");

        Label l1 = new Label("Welcome to PhoneBook");
        Label l2 = new Label("UserName");
        Label l3 = new Label("Password");

        l1.setStyle("-fx-font-size: 25px;");
        l2.setStyle("-fx-font-size: 18px;");
        l3.setStyle("-fx-font-size: 18px;");

        tf = new TextField();
        pf = new PasswordField();

        tf.setStyle("-fx-font-size: 18px;");
        pf.setStyle("-fx-font-size: 18px;");

        Button bt1 = new Button("Login");
        Button bt2 = new Button("Cancel");

        bt1.setOnAction((ActionEvent event) -> {
            String username = tf.getText();
            String password = pf.getText();
            
            //Database connection
            String Url = "jdbc:mysql://localhost:3306/phonemanagement";
            String DBUser = "root";
            String DBPassword = "joshua123";
            
            try (Connection connection = DriverManager.getConnection(Url, DBUser, DBPassword)) {
                String query = "SELECT * FROM login WHERE Username = ? AND Password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("You have logged in.");
                            
                            //Connected my home page to the login page
                            Home home = new Home();
                            home.start(new Stage());
                            
                            primaryStage.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Login Failed");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid user/password");
                            alert.showAndWait();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        bt2.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(new Label("Username:"), tf, new Label("Password:"), pf, bt1);

        // Create a scene and set it on the stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
        
    }
}
