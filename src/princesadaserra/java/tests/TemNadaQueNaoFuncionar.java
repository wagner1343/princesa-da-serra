package princesadaserra.java.tests;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.role.RoleRepository;
import princesadaserra.java.persistence.repository.route.CityRepository;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.persistence.repository.route.SegmentRepository;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.persistence.repository.trip.TripSaleRepository;
import princesadaserra.java.persistence.repository.user.UserRepository;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;

import java.util.List;

public class TemNadaQueNaoFuncionar {

    public static void main(String[] args){

        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool("postgres", "123456");

        UserRepository userRepository = new UserRepository(connectionPool);
        ModelRepository modelRepository = new ModelRepository(connectionPool);
        BusRepository busRepository = new BusRepository(connectionPool);
        TripRepository tripRepository = new TripRepository(connectionPool);
        TripSaleRepository tripSaleRepository = new TripSaleRepository(connectionPool);
        RouteRepository routeRepository = new RouteRepository(connectionPool);
        RoleRepository roleRepository = new RoleRepository(connectionPool);

        Long x = new Long(1);

        Trip trip = tripRepository.find(x);

        /*Role moto = new Role();
        moto.setId(x);
        moto.setName("Motorista");

        Role vend = new Role();
        vend.setId(x+x);
        vend.setName("Vendedor");

        User user = new User();
        user.setFirstName("Tiao");
        user.setId(x);
        user.setCpf(null);
        user.setEmail(null);
        user.setLastName(null);
        user.setPhone(null);
        user.setImageUrl(null);
        user.setPassword("123456");
        user.setRole(moto);
        user.setUsername("Tiao");

        User user1 = new User();
        user1.setFirstName("Nicole");
        user1.setId(x+x);
        user1.setCpf(null);
        user1.setEmail(null);
        user1.setLastName(null);
        user1.setPhone(null);
        user1.setImageUrl(null);
        user1.setPassword("123");
        user1.setRole(vend);
        user1.setUsername("nicol3");

        Model model = new Model();
        model.setSeatAmount(30);
        model.setId(x);
        model.setAxisAmount(0);

        Bus bus = new Bus();
        bus.setModel(model);
        bus.setId(x);
        bus.setLastMaintenance(null);

        Route route = routeRepository.find(new Long(2));

        Trip trip = new Trip();
        trip.setRoute(route);
        trip.setDriver(null);
        trip.setBus(bus);
        trip.setId(x);
        trip.setTimeStart(null);
        trip.setTimeFinish(null);
        trip.setTimeExpected(null);


        modelRepository.add(model);
        busRepository.add(bus);
        tripRepository.add(trip);

        roleRepository.add(moto);
        roleRepository.add(vend);
        userRepository.add(user);
        userRepository.add(user1);*/
    }
}
