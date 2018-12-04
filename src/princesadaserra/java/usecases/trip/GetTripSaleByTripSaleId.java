package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripSaleRepository;
import princesadaserra.java.util.threading.Task;

public class GetTripSaleByTripSaleId extends Task<String, TripSale, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetTripSaleByTripSaleId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected TripSale execute(String useless){

        TripSaleRepository tripSaleRepository = null;
        TripSale tripSale = null;

        try{

            tripSaleRepository = new TripSaleRepository(connectionPool);
            tripSale = new TripSale();
            tripSale = tripSaleRepository.find(key);
            System.out.println("Find tripSale by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return tripSale;
    }
}
