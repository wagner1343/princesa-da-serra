package princesadaserra.java;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage palco) throws Exception {
		
		Parent p = FXMLLoader.load(getClass().getResource("RealizaCompra.fxml"));	
		Scene cena = new Scene(p, 800, 600);
		palco.setTitle("Princesa da Serra");
		palco.setScene(cena);
		palco.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
