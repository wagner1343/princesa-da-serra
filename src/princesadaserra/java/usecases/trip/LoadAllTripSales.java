package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripSaleRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllTripSales extends Task<Boolean, List<TripSale>, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;

    public LoadAllTripSales(PDSDatabaseConnectionPool connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<TripSale> execute(Boolean useless){

        TripSaleRepository tripSaleRepository = null;
        List<TripSale> tripSales = new ArrayList<>();
        try{

            tripSaleRepository = new TripSaleRepository(connectionPool);
            tripSales = tripSaleRepository.list();
            System.out.println("List all tripSales success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return tripSales;
    }
}
