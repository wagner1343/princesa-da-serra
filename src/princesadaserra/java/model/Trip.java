package princesadaserra.java.model;

import java.util.Date;
import java.util.Observable;

public class Trip extends Observable{

	private int id;
	private Route rota;
	private Date dateStart;
	private Date dateFinish;
	private Date dateExpected;
	private User driver;
	private Bus bus;

}
