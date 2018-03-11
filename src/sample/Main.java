package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private Stage window;
    private Scene mainScene;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.initStyle(StageStyle.TRANSPARENT);

        mainScene = new Scene(new formDecorator(window,root));
        mainScene.setFill(null);

        window.setScene(mainScene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
