package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import javax.swing.table.TableColumn;
import java.util.List;
import java.util.function.Predicate;

public class GetBusBySpecification extends Task<String, List<Bus>, Integer> {
    private BusRepository busRepository;
    private Predicate<Bus>[] predicates;

    public GetBusBySpecification(ConnectionPoolDataSource dataSource, Predicate<Bus>[] predicates) {
        busRepository = new BusRepository(dataSource);
        this.predicates = predicates;
    }

    @Override
    protected List<Bus> execute(String argument) {
        System.out.println("GetBusBySpecification.execute");
        List<Bus> buses = busRepository.list();
        for(Predicate<Bus> p : predicates)
            buses.removeIf(p);

        for(Bus b : buses)
            System.out.println(b);
        return buses;
    }
}
