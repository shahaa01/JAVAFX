import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoanCal extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane inputs = new GridPane();
		inputs.setAlignment(Pos.CENTER);
		inputs.setPadding(new Insets(10,10,10,10));
		inputs.setHgap(5);
		inputs.setVgap(5);
		
		TextField p = new TextField();
		inputs.add(new Label("Enter your principal"), 0, 0);
		inputs.add(p, 1, 0);
		
		TextField t = new TextField();
		inputs.add(new Label("Enter your Time"), 0, 1);
		inputs.add(t, 1, 1);

		TextField r = new TextField();
		inputs.add(new Label("Rate"), 0, 3);
		inputs.add(r, 1, 3);
		
		inputs.add(new Label("Total Interest"), 0, 4);
		TextField interestField = new TextField();
		interestField.setEditable(false);
		inputs.add(interestField, 1, 4);
		
		Button calc = new Button("Calculate");
		inputs.add(calc, 1, 5);
		GridPane.setHalignment(calc, HPos.RIGHT);
		
		calc.setOnAction(e -> {
			double interest = (Double.parseDouble(p.getText()) * Double.parseDouble(t.getText()) * Double.parseDouble(r.getText())) / 100.0;
			interestField.setText(interest + "");
		});
		
		StackPane rootPane = new StackPane();
		rootPane.getChildren().add(inputs);
		
		Scene scene = new Scene(rootPane, 300, 300);
		primaryStage.setTitle("LoanCalculator");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] arg) {
		Application.launch(arg);
	}

}
