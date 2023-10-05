package PhoneBook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class EditData extends Application {
    private Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    private Button bt1, bt2;
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf10;

    public EditData() {
    }

    public EditData(int idno) {
        // Initialize your UI components here

        l1 = new Label("Edit Contact");
        l2 = new Label("Name");
        l3 = new Label("Phone No");
        l4 = new Label("Mobile No");
        l5 = new Label("Email Address");
        l6 = new Label("Home Address");
        l7 = new Label("Favourite");
        l8 = new Label("Blocklist");
        l9 = new Label("Group Name");
        l10 = new Label("Id");

        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();
        tf4 = new TextField();
        tf5 = new TextField();
        tf6 = new TextField();
        tf7 = new TextField();
        tf8 = new TextField();
        tf10 = new TextField();

        bt1 = new Button("Edit Contact");
        bt2 = new Button("Back");

        l1.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        l2.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l3.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l4.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l5.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l6.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l7.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l8.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l9.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        l10.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        bt1.setStyle("-fx-background-color: red; -fx-text-fill: black;");
        bt2.setStyle("-fx-background-color: gray; -fx-text-fill: white;");

        bt1.setOnAction((ActionEvent event) -> {
            // Handle button click here
            int Cid = Integer.parseInt(tf10.getText());
            String name = tf1.getText();
            String phone = tf2.getText();
            String mobile = tf3.getText();
            String email = tf4.getText();
            String address = tf5.getText();
            String favourite = tf6.getText();
            String blocklist = tf7.getText();
            String group_name = tf8.getText();
            
            try {
                ConnectionClass obj3 = new ConnectionClass();
                String q1 = "update add_contact set name='" + name + "',phone='" + phone + "',mobile='" + mobile
                        + "',email='" + email + "',address='" + address + "',favourite='" + favourite + "',blocklist='"
                        + blocklist + "',group_name='" + group_name + "'where Id='" + Cid + "'";
                int aa = obj3.stm.executeUpdate(q1);
                if (aa == 1) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Your Data is successfully Updated");
                    successAlert.showAndWait();
                    
                    Stage currentStage = (Stage) bt1.getScene().getWindow();
                    currentStage.close();
                    
                    SearchDataForEdit searchDataForEdit = new SearchDataForEdit();
                    searchDataForEdit.start(new Stage());
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Please Fill the details carefully");
                    errorAlert.showAndWait();
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        });

        bt2.setOnAction((ActionEvent event) -> {
            // Handle button click here
            Stage currentStage = (Stage) bt2.getScene().getWindow();
            currentStage.close();
            
            Home home = new Home();
            home.start(new Stage());
        });

        tf10.setEditable(false);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 450, 650);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, l10, tf10);
        gridPane.addRow(1, l2, tf1);
        gridPane.addRow(2, l3, tf2);
        gridPane.addRow(3, l4, tf3);
        gridPane.addRow(4, l5, tf4);
        gridPane.addRow(5, l6, tf5);
        gridPane.addRow(6, l7, tf6);
        gridPane.addRow(7, l8, tf7);
        gridPane.addRow(8, l9, tf8);
        gridPane.addRow(9, bt1, bt2);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(l1, gridPane);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        root.setCenter(vbox);

        primaryStage.setTitle("Edit Contact");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
