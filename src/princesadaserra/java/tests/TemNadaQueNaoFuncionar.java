package princesadaserra.java.tests;

import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.CityRepository;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.persistence.repository.route.SegmentRepository;

import java.util.List;

public class TemNadaQueNaoFuncionar {

    public static void main(String[] args){

        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool("postgres", "123456");
        CityRepository cityRepository = new CityRepository(connectionPool);
        SegmentRepository segmentRepository = new SegmentRepository(connectionPool);
        RouteRepository routeRepository = new RouteRepository(connectionPool);

        /*City floripa = new City();
        floripa.setId(new Long(1));
        floripa.setName("Floripa");

        City cwb = new City();
        cwb.setId(new Long(2));
        cwb.setName("Cwb");

        City sp = new City();
        sp.setId(new Long(3));
        sp.setName("SP");

        cityRepository.add(floripa);
        cityRepository.add(cwb);
        cityRepository.add(sp);

        Segment seg0 = new Segment();
        seg0.setId(new Long(1));
        seg0.setCityOrigin(floripa);
        seg0.setCityDestination(cwb);
        seg0.setTime(null);
        seg0.setValue(30.5);

        Segment seg1 = new Segment();
        seg1.setId(new Long(2));
        seg1.setCityOrigin(cwb);
        seg1.setCityDestination(sp);
        seg1.setTime(null);
        seg1.setValue(40.8);

        segmentRepository.add(seg0);
        segmentRepository.add(seg1);

        Route route = new Route();
        route.setId(new Long(1));
        route.setName("Curitiba para SaoPaulo");

        routeRepository.add(route);

        City rj = new City();
        rj.setName("Rj");
        City vitoria = new City();
        vitoria.setName("Vitoria");
        City natal = new City();
        natal.setName("Natal");

        cityRepository.add(rj);
        cityRepository.add(vitoria);
        cityRepository.add(natal);

        Segment a = new Segment();
        a.setCityDestination(cityRepository.find(new Long(4)));
        a.setCityOrigin(cityRepository.find(new Long(3)));
        a.setValue(80.3);
        a.setTime(null);

        Segment b = new Segment();
        b.setCityDestination(cityRepository.find(new Long(5)));
        b.setCityOrigin(cityRepository.find(new Long(4)));
        b.setValue(11.3);
        b.setTime(null);

        segmentRepository.add(a);
        segmentRepository.add(b);*/

        Route route = new Route();
        route.setName("Floripa to Rj");
        route.setId(routeRepository.findByName(route.getName()).getId());
        route = routeRepository.fullRoute(route.getId());
        System.out.println(route.getSegments().size());

        //List<Segment> segments = segmentRepository.findByCitySail(new Long(2));


    }
}
