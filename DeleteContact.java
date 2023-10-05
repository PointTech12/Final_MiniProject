package PhoneBook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import javafx.beans.property.SimpleStringProperty;

public class DeleteContact extends Application {
    String[] columns = {"Id", "Name", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    TableView<String[]> table = new TableView<>();
    TextField tf = new TextField();
    Button deleteButton = new Button("Delete");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Contact Information");

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "select * from add_contact";
            ResultSet resultSet = obj.stm.executeQuery(query);

            while (resultSet.next()) {
                String[] rowData = {
                        resultSet.getString("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("mobile"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("favourite"),
                        resultSet.getString("blocklist"),
                        resultSet.getString("group_name")
                };
                table.getItems().add(rowData);
            }

            for (int i = 0; i < columns.length; i++) {
                final int index = i;
                TableColumn<String[], String> column = new TableColumn<>(columns[i]);
                column.setCellValueFactory(param -> {
                    String[] rowData = param.getValue();
                    return new SimpleStringProperty(rowData[index]);
                });
                table.getColumns().add(column);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Label label = new Label("Enter Contact ID for Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        int idno = Integer.parseInt(tf.getText());
        try {
            ConnectionClass obj1 = new ConnectionClass();
            String query = "delete from add_contact where Id='" + idno + "'";
            int result = obj1.stm.executeUpdate(query);
            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your Contact was Successfully Deleted");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        primaryStage.close();
                        Home home = new Home();
                        home.start(new Stage());
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Your Contact is not Deleted");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(label, tf, deleteButton);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, hbox);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
