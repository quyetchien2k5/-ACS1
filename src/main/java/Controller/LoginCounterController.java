package Controller;

import Model.DatabaseConnection;
import View.HomeCounterView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import  Model.DatabaseConnection.*;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.SpreadsheetCellEditor;

import  java.sql.DriverManager.*;

public class LoginCounterController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public PasswordField SignIn__Input_Password;
    public TextField SignIn__Input_Email;
    public Button SignIn__Button_SignIn;
    public Label SignIn__Error_Email;
    public Label SignIn__Error_Password;

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String encodePassword(String password)
    {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for(byte b : encodeHash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            return hexString.toString();

        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void LoginAction(ActionEvent actionEvent) throws Exception{
        if(SignIn__Input_Email.getText().isEmpty()){
            SignIn__Error_Email.setText("Vui lòng nhập email");
            SignIn__Error_Password.setText(" ");
        }else if(isValidEmail(SignIn__Input_Email.getText()) == false){
            SignIn__Error_Email.setText("Email không hợp lệ");
            SignIn__Error_Password.setText(" ");
        }else if(SignIn__Input_Password.getText().isEmpty()){
            SignIn__Error_Password.setText("Vui lòng nhập mật khẩu");
            SignIn__Error_Email.setText(" ");
        }else if(SignIn__Input_Password.getText().length() < 8){
            SignIn__Error_Password.setText("Mật khẩu từ 8 kí tự");
            SignIn__Error_Email.setText(" ");
        }else{
            SignIn__Error_Email.setText(" ");
            SignIn__Error_Password.setText("");

            String query = "SELECT email, password FROM user WHERE email = ? AND password = ?";
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, SignIn__Input_Email.getText());
                preparedStatement.setString(2, encodePassword(SignIn__Input_Password.getText()));
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    HomeCounterView homeCounterView = new HomeCounterView();
                    homeCounterView.start(new Stage());
                }else{
                    SignIn__Error_Password.setText("Tài khoản mật khẩu không hợp lệ");
                }
        }
    }
}
