package controller;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DatabaseConnection;
import model.User;
import model.data;
import view.HomeManagerView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static model.data.encodePassword;

public class LoginManagerController implements Initializable {
    @FXML
    private Button lo_btn;

    @FXML
    private TextField lo_email;

    @FXML
    private PasswordField lo_password;

    @FXML
    private Button re_Btn;

    @FXML
    private TextField re_age;

    @FXML
    private ImageView re_avatar;

    @FXML
    private Button re_avatarBtn;

    @FXML
    private PasswordField re_confirm;

    @FXML
    private TextField re_email;

    @FXML
    private TextField re_gender;

    @FXML
    private TextField re_name;

    @FXML
    private PasswordField re_password;

    @FXML
    private ComboBox<String> re_position;

    @FXML
    private DatePicker re_startWork;

    @FXML
    private TextField re_salary;

    @FXML
    private AnchorPane lo_form;
    @FXML
    private AnchorPane re_form;

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;


    private String[] posList = {"Counter", "Manager", "Client"};

    public void invenTypeList() {
        List<String> typeL = new ArrayList<>();
        for (String data : posList) {
            typeL.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(typeL);
        re_position.setItems(listData);
    }

    public void loginBtn() throws Exception {

        //check the email or password feild do write yet? if not, give notification error!

        if (lo_email.getText().isEmpty() || lo_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter email/password!");
            alert.showAndWait();
        } else {
            String selectData = "SELECT email, password FROM user WHERE email = ? and password = ?";

            connect = DatabaseConnection.getConnection();


            prepare = connect.prepareStatement(selectData);
            prepare.setString(1, lo_email.getText());
            prepare.setString(2, encodePassword(lo_password.getText()));

            result = prepare.executeQuery();

            // Nếu đăng nhập thành công sẽ chuyển sang giao diện chính của chương trình
            if (result.next()) {

                data.currentManager = lo_email.getText();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login!");
                alert.showAndWait();


                //Kết nối với giao diện chính khi đăng nhập thành công.
                HomeManagerView homeManagerView = new HomeManagerView();
                homeManagerView.start(new Stage());

                lo_btn.getScene().getWindow().hide();
                DatabaseConnection.closeConnect(connect);

            } else {
                // Nếu không sẽ xuất hiện thông báo
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Username/Password !");
                alert.showAndWait();

            }
        }
    }

    public void importBtn() {

        FileChooser openFileChooser = new FileChooser();
        openFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));

        File file = openFileChooser.showOpenDialog(lo_form.getScene().getWindow());

        if (file != null) {

            data.pathAvatar = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 70, 70, false, true);

            re_avatar.setImage(image);
        }
    }

    public void reBtn() throws Exception {
        if (re_name.getText().isEmpty() || re_email.getText().isEmpty() || re_gender.getText().isEmpty()
                || re_age.getText().isEmpty() || re_password.getText().isEmpty() || re_position.getSelectionModel().getSelectedItem().isEmpty() || re_startWork.getValue() == null
                || re_salary.getText().isEmpty() || re_avatar.getImage() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();

        } else {
            String reData = "INSERT INTO user(name, email, gender, age, password, position, date, salary, avatar)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";

            connect = DatabaseConnection.getConnection();

            // Kiểm tra nếu email đã tồn tài.
            String checkEmail = "SELECT email FROM user WHERE email = ?";
            prepare = connect.prepareStatement(checkEmail);
            prepare.setString(1, re_email.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(re_email.getText() + " is already taken");
                alert.showAndWait();
            } else if (re_password.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The password must be at least 8 characters long!");
                alert.showAndWait();
            } else if (!Pattern.matches(data.email_patern, re_email.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The email INVALID: example@gmail.com");
                alert.showAndWait();
            } else {
                String selectedPosition = (String) re_position.getSelectionModel().getSelectedItem();
                if (selectedPosition.equals("Manager")) {
                    // Kiểm tra xem đã có bản ghi "Manager" chưa
                    String checkManager = "SELECT COUNT(*) AS manager_count FROM user WHERE position = 'Manager'";
                    prepare = connect.prepareStatement(checkManager);
                    result = prepare.executeQuery();
                    if (result.next() && result.getInt("manager_count") > 0) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("There is already a Manager in the database. Cannot add another Manager.");
                        alert.showAndWait();
                        return; // Dừng phương thức nếu không thể thêm bản ghi Manager mới
                    }
                }

                // Insert dữ liệu vào cơ sở dữ liệu
                LocalDate localDate = re_startWork.getValue();
                java.util.Date utilDate = Date.valueOf(localDate);
                Date sqlDate = new Date(utilDate.getTime());
                int age = Integer.parseInt(re_age.getText());
                Integer salary = Integer.parseInt(re_salary.getText());

                User user = new User(re_name.getText(), re_email.getText(), re_gender.getText(), age, re_password.getText(), sqlDate, null,null, salary, data.pathAvatar);
                UserDAO.getInstance().insert(user);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully registered Account!");
                alert.showAndWait();

                DatabaseConnection.closeConnect(connect);

                clear();
            }
        }
    }


    public void switchLoToReForm(){
        re_form.setVisible(true);
        lo_form.setVisible(false);
    }
    public void switchReToLoForm(){
        re_form.setVisible(false);
        lo_form.setVisible(true);
    }



    public void clear(){
        re_email.setText("");
        re_password.setText("");
        re_age.setText("");
        re_avatar.setImage(null);
        re_gender.setText("");
        re_name.setText("");
        re_salary.setText("");

        re_position.setPromptText("Choose your position...!");


        re_startWork.setPromptText("Choose date start...!");
   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        invenTypeList();
      clear();
    }
}




