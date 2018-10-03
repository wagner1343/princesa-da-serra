package java.testes;

import java.util.Observable;
import java.util.Observer;

import java.model.Viagem;

public class ViagemViewTeste {
	
	
	ViagemViewModelTeste viewModel;
	
	String t; 
	
	private void init(){
		viewModel.recentes().addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				t = (arg.getClass().toString());
				System.out.println("aaa");
			}
		});
	}
	
	public void test() {
		Viagem v = new Viagem();
		v.setId(1);
		t = "6";
		viewModel =  new ViagemViewModelTeste();
		viewModel.setV(v);
		init();
		System.out.println(t);
		v.setId(4);
		v.notifyObservers(v);
		System.out.println(t);
	}
	
}

