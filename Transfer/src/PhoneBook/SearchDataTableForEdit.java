package PhoneBook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;

public class SearchDataTableForEdit extends Application {
    private String[] columns = {"Id", "Name", "Phone", "Mobile", "Email", "Address", "Favourite", "Blocklist", "Group Name"};
    private String[][] data = new String[20][9];
    private int i = 0, j = 0;
    private TableView<String[]> tableView = new TableView<>();
    private Label label = new Label("Enter your Contact ID:");
    private TextField textField = new TextField();
    private Button editButton = new Button("Edit");

    public SearchDataTableForEdit() {
    }

    public SearchDataTableForEdit(String name1) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "select * from add_contact where name='" + name1 + "'";
            ResultSet resultSet = obj.stm.executeQuery(query);
            while (resultSet.next()) {
                data[i][j++] = resultSet.getString("Id");
                data[i][j++] = resultSet.getString("name");
                data[i][j++] = resultSet.getString("phone");
                data[i][j++] = resultSet.getString("mobile");
                data[i][j++] = resultSet.getString("email");
                data[i][j++] = resultSet.getString("address");
                data[i][j++] = resultSet.getString("favourite");
                data[i][j++] = resultSet.getString("blocklist");
                data[i][j++] = resultSet.getString("group_name");
                i++;
                j = 0;
            }
            tableView = new TableView<>();
            for (int col = 0; col < columns.length; col++) {
                final int finalCol = col;
                TableColumn<String[], String> column = new TableColumn<>(columns[col]);
                column.setCellValueFactory(param -> {
                    String[] row = param.getValue();
                    return new SimpleStringProperty((row != null && row.length > finalCol) ? row[finalCol] : "");
                });
                tableView.getColumns().add(column);
            }
            tableView.getItems().addAll(Arrays.asList(data).subList(0, i));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        editButton.setOnAction((ActionEvent event) -> {
            int idno = Integer.parseInt(textField.getText());
            new EditData(idno).start(new Stage());
            ((Stage) textField.getScene().getWindow()).close();
        });
    }

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox(tableView);
        HBox hbox = new HBox(label, textField, editButton);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        BorderPane borderPane = new BorderPane(vbox);
        borderPane.setBottom(hbox);
        Scene scene = new Scene(borderPane, 800, 350);
        stage.setTitle("Contact Information");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
