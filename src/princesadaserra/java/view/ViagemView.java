package java.view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.TextField;
import java.model.Viagem;
import java.viewmodel.ViagemViewModel;

public class ViagemView {
	
	
	ViagemViewModel viewModel;
	
	TextField t; 
	
	private void init(){
		viewModel.recentes().addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				
				t.setText(arg.getClass().toString());
			}
		});
	}
}
