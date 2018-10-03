package java.testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import java.model.Cidade;
import java.model.Viagem;
import princesadaserra.java.ui.view.trips.TripsViewModel;

public class TripsViewModelTeste implements TripsViewModel {

	Viagem v;
	@Override
	public void buscarViagemOnClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Viagem> buscaViagem(Cidade saida, Cidade chegada, Date dataSaida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable recent() {
		
		return v;
	}
	
	public void changeId(int i) {
		this.v.setId(i);
	}
	
	public void setV(Viagem v) {
		
		this.v = v;
	}
	
}
