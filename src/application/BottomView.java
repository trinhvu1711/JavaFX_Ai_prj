package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BottomView extends HBox{
	private ImageView imgIcon;
	private TextField text;
	private Button button;
	public BottomView(Controller controller) {
		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(10));
		text = new TextField();
		text.setPromptText("Ask Bot");
		text.setFont(Font.font("Ebrima", 20));
		text.setPadding(new Insets(5));
		text.setMinWidth(332);
		text.setStyle("-fx-background-color: none");
		Image image = new Image(getClass().getResourceAsStream("microphone.png"));
		imgIcon = new ImageView(image);
		imgIcon.setFitHeight(39);
		imgIcon.setFitWidth(39);
		Button button = new Button();
		button.setGraphic(imgIcon);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, controller);
		button.setStyle("-fx-background-color: none");
		this.getChildren().add(text);
		getChildren().add(button);
		setMargin(imgIcon, new Insets(5));
		
	}

	public ImageView getImgIcon() {
		return imgIcon;
	}

	public void setImgIcon(ImageView imgIcon) {
		this.imgIcon = imgIcon;
	}

	public TextField getText() {
		return text;
	}

	public void setText(TextField text) {
		this.text = text;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
}
