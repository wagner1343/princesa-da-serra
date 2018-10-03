package java.model;

import java.util.Date;
import java.util.Observable;

public class Viagem extends Observable{

	private int id;
	private Rota rota;
	private Date dataPartida;
	private Date dataChegada;
	private Date dataPrevista;
	private Usuario motorista;
	private Onibus onibus;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		setChanged();
		notifyObservers(this);
		
	}
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	public Date getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	public Date getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public Usuario getMotorista() {
		return motorista;
	}
	public void setMotorista(Usuario motorista) {
		this.motorista = motorista;
	}
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	
	
	public String toString() {
		
		return Integer.toString(this.getId());
	}

}
