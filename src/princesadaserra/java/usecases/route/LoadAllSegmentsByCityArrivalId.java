package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Segment;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.SegmentRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllSegmentsByCityArrivalId extends Task<Boolean, List<Segment>, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public LoadAllSegmentsByCityArrivalId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected List<Segment> execute(Boolean useless){

        SegmentRepository segmentRepository = null;
        List<Segment> segmentes = new ArrayList<>();
        try{

            segmentRepository = new SegmentRepository(connectionPool);
            segmentes = segmentRepository.findByCityArrival(key);
            System.out.println("List all segmentes success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return segmentes;
    }
}
