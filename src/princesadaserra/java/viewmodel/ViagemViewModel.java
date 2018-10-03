package java.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javafx.collections.ObservableArray;
import java.model.Cidade;
import java.model.Viagem;

public interface ViagemViewModel extends ViewModel{
	
	public void buscarViagemOnClick();
	public ArrayList<Viagem> buscaViagem(Cidade saida, Cidade chegada, Date dataSaida);
	
	public Observable recentes();
}
