package controller;

import dao.MenuDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DatabaseConnection;
import model.Menu;
import model.User;
import model.data;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.sql.Types.NULL;

public class HomeManagerController implements Initializable {
    @FXML
    private DatePicker counter_job;
    @FXML
    private TextField counter_Name;

    @FXML
    private TextField counter_workingdays;

    @FXML
    private Label counter_Salary;

    @FXML
    private TextField counter_Search;

    @FXML
    private Button counter_addBtn;

    @FXML
    private Button counter_clearBtn;

    @FXML
    private TableColumn<?, ?> counter_col_Job;

    @FXML
    private TableColumn<?, ?> counter_col_Name;

    @FXML
    private TableColumn<?, ?> counter_col_WorkingDays;

    @FXML
    private TableColumn<?, ?> counter_col_date;

    @FXML
    private TableColumn<?, ?> counter_col_salary;

    @FXML
    private Button counter_deleteBtn;

    @FXML
    private AnchorPane counters_form;

    @FXML
    private ImageView counter_image;

    @FXML
    private Button counter_importbtn;

    @FXML
    private Button counter_searchBtn;

    @FXML
    private TableView<User> counter_table;

    @FXML
    private Button counter_updateBtn;

    @FXML
    private Button customers_btn;

    @FXML
    private TableColumn<?, ?> customers_col_ID;

    @FXML
    private TableColumn<?, ?> customers_col_cashier;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_totals;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private TableView<?> customers_table;

    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_NSP;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TotalI;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField menu_DishID;

    @FXML
    private TextField menu_Name;

    @FXML
    private TextField menu_Price;

    @FXML
    private TextField menu_Search;

    @FXML
    private ComboBox<String> menu_Type;

    @FXML
    private Button menu_addBtn;

    @FXML
    private Button menu_btn;

    @FXML
    private Button menu_clearBtn;

    @FXML
    private TableColumn<?, ?> menu_col_ID;

    @FXML
    private TableColumn<?, ?> menu_col_Name;

    @FXML
    private TableColumn<?, ?> menu_col_Price;

    @FXML
    private TableColumn<?, ?> menu_col_Type;

    @FXML
    private Button menu_deleteBtn;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private ImageView menu_image;

    @FXML
    private Button menu_importBtn;

    @FXML
    private Button menu_searchBtn;

    @FXML
    private TableView<Menu> menu_table;

    @FXML
    private Button menu_updateBtn;

    @FXML
    private Button staffs_btn;

    @FXML
    private AnchorPane main_form;
    @FXML
    private TextField counter_bonus;
    @FXML
    private DatePicker counter_dateStart;

    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private Image image;

    private String[] typeList = {"Món khai vị", "Món cá", "Món tôm", "Món cua", "Món mực", "Nghêu, hàu, sò,ốc", "Món lẩu", "Món thập cẩm", "Món rau", "Món khác", "Tráng miệng", "Thức uống"};
    public void menuTypeList() {
        List<String> typeL = new ArrayList<>();
        for(String data: typeList) {
            typeL.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(typeL);
        menu_Type.setItems(listData);
    }

    public void addMenuBtn() throws Exception {

        if (menu_DishID.getText().isEmpty()
                || menu_Name.getText().isEmpty()
                || menu_Price.getText().isEmpty()
                || menu_Type.getSelectionModel().getSelectedItem() == null
                || data.pathMenuImage == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();

        } else {
            String checkMenuID = "SELECT id FROM menu WHERE id = '" + menu_DishID.getText()+ "'";

            connect = DatabaseConnection.getConnection();

            statement = connect.createStatement();

            result = statement.executeQuery(checkMenuID);

            if (result.next()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(menu_DishID.getText() + " is already taken!");
                alert.showAndWait();

            } else {
                // Insert dữ liệu vào cơ sở dữ liệu
                String selectedType = (String) menu_Type.getSelectionModel().getSelectedItem();
                double price = Double.parseDouble(menu_Price.getText());

                Menu menu = new Menu(menu_DishID.getText(),
                        menu_Name.getText(),
                        selectedType,
                        data.pathMenuImage,
                        price);
                MenuDAO.getInstance().insert(menu);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully registered Account!");
                alert.showAndWait();

                DatabaseConnection.closeConnect(connect);

                menuShowData();

                clearMenuBtn();
            }
        }
    }
    public void updateMenuBtn() throws Exception {

        if (menu_DishID.getText().isEmpty()
                || menu_Name.getText().isEmpty()
                || menu_Price.getText().isEmpty()
                || menu_Type.getSelectionModel().getSelectedItem() == null
                || data.pathMenuImage == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();

        } else {
            String path = data.pathMenuImage;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE menu SET "
                    + "id = '" + menu_DishID.getText() +"', name = '"
                    + menu_Name.getText()+"', type ='"
                    + menu_Type.getSelectionModel().getSelectedItem()+"', price = '"
                    + menu_Price.getText()+"', image = '"
                    + path + "' WHERE id = '" + data.oldMenuId+"'";


            connect = DatabaseConnection.getConnection();

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to UPDATE id: " + data.oldMenuId + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                prepare = connect.prepareStatement(updateData);

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                // Hiện thị lại cái đã caạp nhật lên bảng.
                menuShowData();
                // Đặt lại giá trị rỗng cho các label.
                clearMenuBtn();

            }
        }
    }
    public void deleteMenuBtn() throws Exception {

        if (data.oldMenuId==null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to DELETE id: " + menu_DishID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM menu WHERE id = '" + data.oldMenuId+"'";
                prepare = connect.prepareStatement(deleteData);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Hiện thị lại dữ liệu lên bảng.
                menuShowData();
                // Đặt lại giá trị rỗng cho các label.
                clearMenuBtn();
            }
        }

    }

    public ObservableList<Menu> menuDataList() throws Exception {

        ObservableList<Menu> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM menu";

        connect = DatabaseConnection.getConnection();

        prepare = connect.prepareStatement(sql);

        result = prepare.executeQuery();

        Menu menu;

        while (result.next()) {
            menu = new Menu(result.getString("id"),
                    result.getString("name"),
                    result.getString("type"),
                    result.getString("image"),
                    result.getDouble("price")
                    );
            listData.add(menu);
        }
        return listData;
    }
    private ObservableList<Menu> menuListData;

    public void menuShowData() throws Exception {
        menuListData = menuDataList();

        menu_col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        menu_col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        menu_col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        menu_col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        menu_table.setItems(menuListData);

    }
    public void menuSelectData() {

        Menu menu = menu_table.getSelectionModel().getSelectedItem();
        int num = menu_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        menu_DishID.setText(menu.getId());
        menu_Name.setText(menu.getName());
        menu_Price.setText(String.valueOf(menu.getPrice()));

        data.pathMenuImage = menu.getImage();
        String path = "File:" + menu.getImage();
        data.oldMenuId = menu.getId();
        image = new Image(path, 120, 130, false, true);

        menu_image.setImage(image);

    }
    public void clearMenuBtn() {

        // if(event.getSource()==inven_clearBtn) {
        menu_DishID.setText("");
        menu_Name.setText("");
        menu_Price.setText("");
        menu_Type.setPromptText("Choose Type...");

        data.pathMenuImage = "";
        menu_image.setImage(null);

    }
    public void importMenuBtn() {

        FileChooser openFileChooser = new FileChooser();
        openFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));

        File file = openFileChooser.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.pathMenuImage = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 70, 70, false, true);

            menu_image.setImage(image);
        }
    }

    public void addCounterBtn() throws Exception {

        if (counter_Name.getText().isEmpty()
                || counter_workingdays.getText().isEmpty()
                || counter_job.getValue().toString().isEmpty()
                || data.pathAvatar == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();

        } else {
            String checkMenuID = "SELECT name FROM user WHERE name = '" + counter_Name.getText()+ "'";

            connect = DatabaseConnection.getConnection();

            statement = connect.createStatement();

            result = statement.executeQuery(checkMenuID);

            if (result.next()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(counter_Name.getText() + " is already taken!");
                alert.showAndWait();

            } else {
                // Insert dữ liệu vào cơ sở dữ liệu
                String workingDaysText = counter_workingdays.getText(); // Lấy văn bản từ Label counter_workingdays
                String bonusText = counter_bonus.getText();

// Chuyển đổi chuỗi số ngày làm việc thành kiểu int
                int workingDays = Integer.parseInt(workingDaysText);
                double bonus = Double.parseDouble(bonusText);

// Tính lương tổng cộng
                double salary = 360000 * workingDays + bonus;
                LocalDate localDate = counter_dateStart.getValue();
                java.util.Date utilDate = Date.valueOf(localDate);
                Date sqlDate = new Date(utilDate.getTime());
                LocalDate localDate1 = counter_job.getValue();
                java.util.Date utilDate1 = Date.valueOf(localDate1);
                Date sqlDate1 = new Date(utilDate1.getTime());
                User user = new User(counter_Name.getText(),null, null,NULL,null,sqlDate,sqlDate1,Integer.valueOf(counter_workingdays.getText()),salary,data.pathAvatar);
                UserDAO.getInstance().insert(user);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully registered Account!");
                alert.showAndWait();

                DatabaseConnection.closeConnect(connect);

                counterShowData();

                clearCounterBtn();

            }

        }
    }
    public void updateCounterBtn() throws Exception {

        if (counter_Name.getText().isEmpty()
                || counter_workingdays.getText().isEmpty()
                || counter_job.getValue().toString().isEmpty()
                || data.pathAvatar == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();

        } else {
            String path = data.pathAvatar;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE user SET "
                    + "name = '"
                    + counter_Name.getText()+"', date ='"
                    + counter_dateStart.getValue().toString()+"', salary = '"
                    + Double.valueOf(counter_bonus.getText())+360000*Integer.valueOf(counter_workingdays.getText())+"', image = '"
                    + path + "' WHERE name = '" + data.oldCounterName+"'";


            connect = DatabaseConnection.getConnection();

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to UPDATE name: " + data.oldCounterName + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                prepare = connect.prepareStatement(updateData);

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                // Hiện thị lại cái đã caạp nhật lên bảng.
                counterShowData();
                // Đặt lại giá trị rỗng cho các label.
                clearCounterBtn();

            }
        }
    }
    public void deleteCounterBtn() throws Exception {

        if (data.oldCounterName==null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to DELETE name: " + counter_Name.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM user WHERE name = '" + data.oldCounterName+"'";
                prepare = connect.prepareStatement(deleteData);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Hiện thị lại dữ liệu lên bảng.
                counterShowData();
                // Đặt lại giá trị rỗng cho các label.
                clearCounterBtn();
            }
        }

    }

    public ObservableList<User> counterDataList() throws Exception {

        ObservableList<User> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM user where email IS NULL";

        connect = DatabaseConnection.getConnection();

        prepare = connect.prepareStatement(sql);

        result = prepare.executeQuery();

        User user;

        while (result.next()) {
                    user = new User(result.getString("name"),
                    result.getDate("date"),
                    result.getDate("job"),
                    result.getInt("workingdays"),
                    result.getDouble("salary"),
                    result.getString("avatar"));

                    listData.add(user);
        }
        for(User user1:listData){
            System.out.println(user1.getName());
        }
        return listData;
    }
    private ObservableList<User> counterListData;

    public void counterShowData() throws Exception {
        counterListData = counterDataList();

        counter_col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        counter_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        counter_col_Job.setCellValueFactory(new PropertyValueFactory<>("job"));
        counter_col_WorkingDays.setCellValueFactory(new PropertyValueFactory<>("workingdays"));
        counter_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        counter_table.setItems(counterListData);

    }
    public void counterSelectData() {

        User user = counter_table.getSelectionModel().getSelectedItem();
        int num = counter_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        counter_Name.setText(user.getName());
        counter_dateStart.setValue(user.getDate().toLocalDate());
        counter_job.setValue(user.getJob().toLocalDate());

        data.pathAvatar = user.getAvatar();
        String path = "File:" + user.getAvatar();
        data.oldCounterName = user.getName();
        image = new Image(path, 120, 130, false, true);

        counter_image.setImage(image);

    }
    public void clearCounterBtn() {

        // if(event.getSource()==inven_clearBtn) {
        counter_Name.setText("");
        counter_bonus.setText("");
        counter_job.getEditor().clear();
        counter_dateStart.getEditor().clear();

        data.pathAvatar = "";
        counter_image.setImage(null);

    }
    public void importCounterBtn() {

        FileChooser openFileChooser = new FileChooser();
        openFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));

        File file = openFileChooser.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.pathAvatar = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 70, 70, false, true);

            counter_image.setImage(image);
        }
    }

    public void switchForm(ActionEvent event) throws Exception {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            counters_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

        } else if (event.getSource() == menu_btn) {
            dashboard_form.setVisible(false);
            counters_form.setVisible(false);
            menu_form.setVisible(true);
            customers_form.setVisible(false);

            menuTypeList();
            menuShowData();
        } else if (event.getSource() == staffs_btn) {
            dashboard_form.setVisible(false);
            counters_form.setVisible(true);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

            counterShowData();
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            counters_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(true);
        }

    }
    public void logout() {

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM
                logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT
                Parent root = FXMLLoader.load(getClass().getResource("..//FXML//loginManager.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("Restaurant Management System");

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearMenuBtn();
        menuTypeList();
        clearCounterBtn();
        try {
            menuShowData();
            counterShowData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
