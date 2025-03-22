import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage stage) throws Exception {
		FlowPane rootPane = new FlowPane(Orientation.VERTICAL);
		rootPane.setPadding(new Insets(50,50,50,50));
		FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
		pane.setPadding(new Insets(20,20,20,20));
        pane.setHgap(50);
        pane.setVgap(50);
		
		Circle circle = new Circle();
		circle.setRadius(50);
		
		Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Flag_of_Nepal.svg/640px-Flag_of_Nepal.svg.png");
		pane.getChildren().add(new ImageView(image));
		
		ImageView imageV2 = new ImageView(image);
		imageV2.setFitHeight(100);
		imageV2.setFitWidth(100);
		pane.getChildren().add(imageV2);
		
		
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		
		circle.setStyle("-fx-stroke: black; -fx-fill:blue;"); //set style
		
	
		
		pane.getChildren().add(circle);
		
		FlowPane pane2 = new FlowPane(Orientation.VERTICAL);
		pane2.getChildren().addAll(
				new Label("Name: "), new TextField(),
				new Label("age")
				);
		TextField tfAge = new TextField();
		tfAge.setPrefColumnCount(1);
		pane.getChildren().add(tfAge);
		
		
		rootPane.getChildren().addAll(pane, pane2);
		


		Scene scene = new Scene(rootPane, 300,300);
		stage.setTitle("Prac");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}