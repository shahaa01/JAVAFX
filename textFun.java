import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class textFun extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane textPane = new Pane();
		Text txt = new Text(120, 120, "Coding is fun");
		textPane.getChildren().add(txt);
		txt.setOnMouseDragged(e -> {
			txt.setX((e.getX() > 0 && e.getX() < textPane.getWidth() - txt.getLayoutBounds().getWidth()) ? e.getX() : txt.getX());
			txt.setY((e.getY() > 0 && e.getX() < textPane.getHeight() - txt.getLayoutBounds().getHeight()) ? e.getY() : txt.getY());
		});
		
		HBox hBox2 = new HBox();
		Button btnUp = new Button("Up");
		Button btnDown = new Button("Down");
		Button btnLeft = new Button("Left");
		Button btnRight = new Button("Right");
		
		hBox2.getChildren().addAll(btnUp, btnDown, btnLeft, btnRight);
		hBox2.setAlignment(Pos.CENTER);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(6,6,6,6));
		pane.setCenter(textPane);
		pane.setBottom(hBox2);
		
		//all handlers
		btnUp.setOnAction(e -> {
			txt.setY(txt.getY() > 10 ? txt.getY() - 5 : 10);
		});
		
		btnDown.setOnAction(e -> {
			txt.setY(txt.getY() < pane.getHeight() - 10 ? txt.getY() + 5 : txt.getY());
		});
		
		btnLeft.setOnAction(e -> {
			txt.setX(txt.getX() > 10 ? txt.getX() - 5 : 10);
		});
		
		btnRight.setOnAction(e -> {
			txt.setX(txt.getX() + txt.getLayoutBounds().getWidth() < textPane.getWidth() - 5 ? txt.getX() + 5 : txt.getX());
		});
		
		Scene scene = new Scene(pane, 300, 300);
		primaryStage.setTitle("Prac");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
