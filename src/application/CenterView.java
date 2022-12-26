package application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CenterView extends VBox {
	Image image = new Image(getClass().getResourceAsStream("bot.png"));

	public CenterView(Controller controller) {
		this.setPrefHeight(USE_COMPUTED_SIZE);
		this.setMaxWidth(400);
		this.setPadding(new Insets(10));
//		this.setOrientation(Orientation.VERTICAL);
		setStyle("-fx-background-color: white");
		// Bot msg
		// row 1
		HBox chat1 = new HBox();
		chat1.setAlignment(Pos.CENTER_LEFT);
		chat1.setPadding(new Insets(10));

		ImageView imgIcon1 = new ImageView(image);
		imgIcon1.setFitHeight(61.0);
		imgIcon1.setFitWidth(58.0);

		Label label1 = new Label("Hi there.");
		label1.setMaxWidth(272);
		label1.setFont(Font.font("Ebrima", 16));
		label1.setWrapText(true);
		label1.setStyle("-fx-border-radius: 10; -fx-border-color: #DCDCDC; -fx-padding: 5px;");

		chat1.getChildren().add(imgIcon1);
		chat1.getChildren().add(label1);
		chat1.setMargin(imgIcon1, new Insets(0, 10, 0, 0));

		this.getChildren().add(chat1);
		// row 2
		HBox chat2 = new HBox();
		chat2.setAlignment(Pos.CENTER_LEFT);
		chat2.setPadding(new Insets(10));

		ImageView imgIcon2 = new ImageView(image);
		imgIcon2.setFitHeight(61.0);
		imgIcon2.setFitWidth(58.0);

		Label label2 = new Label(
				"Talk to me by typing in the text box or select the microphone icon to use your voice.");
		label2.setMaxWidth(272);
		label2.setWrapText(true);
		label2.setFont(Font.font("Ebrima", 16));
		label2.setStyle("-fx-border-radius: 10; -fx-border-color: #DCDCDC; -fx-padding: 5px;");

		chat2.getChildren().add(imgIcon2);
		chat2.getChildren().add(label2);
		chat2.setMargin(imgIcon2, new Insets(0, 10, 0, 0));

		this.getChildren().add(chat2);

	}

	public void addChatBot(String text) {
		HBox chat = new HBox();
		chat.setAlignment(Pos.CENTER_LEFT);
		chat.setPadding(new Insets(10));

		ImageView imgIcon = new ImageView(image);
		imgIcon.setFitHeight(61.0);
		imgIcon.setFitWidth(58.0);

		Label label = new Label(text);
		label.setMaxWidth(272);
		label.setWrapText(true);
		label.setFont(Font.font("Ebrima", 16));
		label.setStyle("-fx-border-radius: 10; -fx-border-color: #DCDCDC; -fx-padding: 5px;");

		chat.getChildren().add(imgIcon);
		chat.getChildren().add(label);
		chat.setMargin(imgIcon, new Insets(0, 10, 0, 0));
		this.getChildren().add(chat);
	}

	public void addChatHuman(String text) {
		HBox chat = new HBox();
		chat.setPadding(new Insets(10));
		chat.setMinWidth(USE_COMPUTED_SIZE);
		chat.setAlignment(Pos.CENTER_RIGHT);
		Label label = new Label(text);
		label.setMaxWidth(272);
		label.setWrapText(true);
		label.setFont(Font.font("Ebrima", 16));
		label.setStyle(
				"-fx-border-radius: 10; -fx-background-color: #DADADA;-fx-background-radius: 10; -fx-padding: 5px;");
		chat.getChildren().add(label);
		this.getChildren().add(chat);
	}
}
