package PhoneBook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntryData extends Application {
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Contact");

        Label l1 = new Label("Add Contact");
        l1.setStyle("-fx-font-size: 25px;");

        Label l2 = new Label("Name");
        Label l3 = new Label("Phone No");
        Label l4 = new Label("Mobile No");
        Label l5 = new Label("Email Address");
        Label l6 = new Label("Home Address");
        Label l7 = new Label("Favourite");
        Label l8 = new Label("Blocklist");
        Label l9 = new Label("Group Name");

        l2.setStyle("-fx-font-size: 15px;");
        l3.setStyle("-fx-font-size: 15px;");
        l4.setStyle("-fx-font-size: 15px;");
        l5.setStyle("-fx-font-size: 15px;");
        l6.setStyle("-fx-font-size: 15px;");
        l7.setStyle("-fx-font-size: 15px;");
        l8.setStyle("-fx-font-size: 15px;");
        l9.setStyle("-fx-font-size: 15px;");

        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();
        tf4 = new TextField();
        tf5 = new TextField();
        tf6 = new TextField();
        tf7 = new TextField();
        tf8 = new TextField();
        tf9 = new TextField();

        tf1.setStyle("-fx-font-size: 15px;");
        tf2.setStyle("-fx-font-size: 15px;");
        tf3.setStyle("-fx-font-size: 15px;");
        tf4.setStyle("-fx-font-size: 15px;");
        tf5.setStyle("-fx-font-size: 15px;");
        tf6.setStyle("-fx-font-size: 15px;");
        tf7.setStyle("-fx-font-size: 15px;");
        tf8.setStyle("-fx-font-size: 15px;");
        tf9.setStyle("-fx-font-size: 15px;");

        Button bt1 = new Button("Add Contact");
        Button bt2 = new Button("Back");

        bt1.setStyle("-fx-font-size: 15px;");
        bt2.setStyle("-fx-font-size: 15px;");

        bt1.setOnAction((ActionEvent event) -> {
            String name = tf1.getText();
            String phone = tf2.getText();
            String mobile = tf3.getText();
            String email = tf4.getText();
            String address = tf5.getText();
            String company = tf6.getText();
            String position = tf7.getText();
            String group = tf8.getText();
            
            if (name.isEmpty() || phone.isEmpty() || mobile.isEmpty() || email.isEmpty() || address.isEmpty() ||
                    company.isEmpty() || position.isEmpty() || group.isEmpty()) {
                // Display an error message here if any field is empty.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the details.");
                alert.showAndWait();
            } else {
                // Attempt to insert data into the database
                String Url = "jdbc:mysql://localhost:3306/PhoneManagement";
                String User = "root";
                String Password = "joshua123";
                
                try (Connection connection = DriverManager.getConnection(Url, User, Password)) {
                    String query = "INSERT INTO add_contact (name, phone, mobile, email, address, favourite, blocklist, group_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, phone);
                        preparedStatement.setString(3, mobile);
                        preparedStatement.setString(4, email);
                        preparedStatement.setString(5, address);
                        preparedStatement.setString(6, company);
                        preparedStatement.setString(7, position);
                        preparedStatement.setString(8, group);
                        
                        int rowsAffected = preparedStatement.executeUpdate();
                        
                        if (rowsAffected == 1) {
                            // Data inserted successfully
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Contact added successfully.");
                            
                            
                            alert.showAndWait();
                            primaryStage.close();
                            Home home = new Home();
                            home.start(new Stage());
                        } else {
                            // Insertion failed
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to add contact. Please try again.");
                            alert.showAndWait();
                        }
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        bt2.setOnAction((ActionEvent event) -> {
            primaryStage.close();
            new Home().start(new Stage());
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                l1, l2, tf1, l3, tf2, l4, tf3, l5, tf4, l6, tf5, l7, tf6, l8, tf7, l9, tf8, bt1, bt2);

        
        Scene scene = new Scene(vbox, 400, 800);
        primaryStage.setScene(scene);

        primaryStage.show();
            
    }
}
