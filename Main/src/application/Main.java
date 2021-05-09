package application;

// 버전 1.1
import java.io.IOException;

import application.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("번호추천");

        initRootLayout();
	}

	public void initRootLayout() {
        try {
            // fxml 파일에서 상위 레이아웃을 가져온다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("view/MainView.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // 상위 레이아웃을 포함하는 scene을 보여준다.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            MainController controller = loader.getController();
            controller.setMainStage(primaryStage);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
        
	public static void main(String[] args) {
		launch(args);
	}
}
