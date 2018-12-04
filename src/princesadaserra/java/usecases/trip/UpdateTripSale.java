package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripSaleRepository;
import princesadaserra.java.util.threading.Task;

public class UpdateTripSale extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private TripSale tripSale = null;

    public UpdateTripSale(PDSDatabaseConnectionPool connectionPool, TripSale tripSale){

        this.connectionPool = connectionPool;
        this.tripSale = tripSale;
    }

    @Override
    protected Boolean execute(String useless){

        TripSaleRepository tripSaleRepository = null;

        try{

            tripSaleRepository = new TripSaleRepository(connectionPool);
            tripSaleRepository.update(tripSale);
            System.out.println("Update tripSale success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
