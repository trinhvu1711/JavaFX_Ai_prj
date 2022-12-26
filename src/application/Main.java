package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Controller().getView(), 400, 720);
		primaryStage.setScene(scene) ;
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("bot.png")));
		primaryStage.setTitle("AI Botchat");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
