package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripSaleRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllTripSalesBySaleId extends Task<Boolean, List<TripSale>, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public LoadAllTripSalesBySaleId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected List<TripSale> execute(Boolean useless){

        TripSaleRepository tripSaleRepository = null;
        List<TripSale> tripSalees = new ArrayList<>();
        try{

            tripSaleRepository = new TripSaleRepository(connectionPool);
            tripSalees = tripSaleRepository.findByUser(key);
            System.out.println("List all tripSales by user success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return tripSalees;
    }
}
