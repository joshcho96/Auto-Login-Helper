package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller {

    Stage thisStage;
    Scene thisScene;

    @FXML
    private JFXTextField idSave;
    @FXML
    private JFXPasswordField pwSave;

    private String id,pw;

    public void closeBtn(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sample.fxml"));

        thisStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();
    }

    public void loginClick(ActionEvent actionEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sceneLogin.fxml"));

        thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();
    }

    public void instagramClicked(ActionEvent actionEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sceneInstagram.fxml"));

        thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();
    }

    public void facebookClicked(ActionEvent actionEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sceneFacebook.fxml"));

        thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();
    }

    public void naverClicked(ActionEvent actionEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sceneNaver.fxml"));

        thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();

        NaverCrawler naver = new NaverCrawler(id, pw);
        naver.loginNaver();
        //

    }

    public void invenClicked(ActionEvent actionEvent) throws IOException {
        Parent loginClick = FXMLLoader.load(getClass().getResource("sceneInven.fxml"));

        thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        thisScene = new Scene(new formDecorator(thisStage ,loginClick));

        thisStage.setScene(thisScene);
        thisStage.show();
    }

    @FXML
    public void saveIDPW(ActionEvent actionEvent) {
        id = idSave.getText();
        pw = pwSave.getText();
    }
}
