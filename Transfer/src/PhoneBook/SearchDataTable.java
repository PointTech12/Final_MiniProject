package PhoneBook;

import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.ResultSet;

public class SearchDataTable extends Application {
    private String[] columns = {"Id", "Name", "Phone", "Mobile", "Email", "Address", "Company", "Position", "Group Name"};
    private String[][] data = new String[20][9];
    private int i = 0, j = 0;
    private TableView<String[]> tableView = new TableView<>();

    public SearchDataTable() {
    }

    public SearchDataTable(String name1) {
        super();
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 800, 350);
        stage.setTitle("Contact Information");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
