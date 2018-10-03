package princesadaserra.java.ui.view.trips;

import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

public class TripsView {
	
	
	TripsViewModel viewModel;
	
	TextField t; 
	
	private void init(){
		viewModel.recent().addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				
				t.setText(arg.getClass().toString());
			}
		});
	}
}
