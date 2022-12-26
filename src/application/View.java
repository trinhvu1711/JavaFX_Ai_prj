package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View extends BorderPane{
	BottomView bottomView;
	CenterView centerView;
	public View() {
		// TODO Auto-generated constructor stub
	}
	public View(BottomView bottomView, CenterView centerView) {
		super();
		this.bottomView = bottomView;
		this.centerView = centerView;
		Image image = new Image(getClass().getResourceAsStream("search.gif"));
		ImageView imgIcon = new ImageView(image);
		imgIcon.setFitHeight(62);
		imgIcon.setFitWidth(62);
		centerView.setStyle("-fx-background-color: none");
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(centerView);
		scrollPane.setFitToWidth(true);
		scrollPane.setMinHeight(600);
		this.setStyle("-fx-background-color: white");
		this.setBottom(bottomView);
		this.setCenter(scrollPane);
		this.setTop(imgIcon);
		this.setAlignment(imgIcon, Pos.CENTER);
	}
	

}
