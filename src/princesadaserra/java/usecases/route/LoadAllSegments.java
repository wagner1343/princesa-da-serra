package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Segment;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.SegmentRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import java.util.ArrayList;
import java.util.List;

public class LoadAllSegments extends Task<Boolean, List<Segment>, Integer> {

    private ConnectionPoolDataSource connectionPool = null;

    public LoadAllSegments(ConnectionPoolDataSource connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Segment> execute(Boolean useless){

        SegmentRepository segmentRepository = null;
        List<Segment> segmentes = new ArrayList<>();
        try{

            segmentRepository = new SegmentRepository(connectionPool);
            segmentes = segmentRepository.list();
            System.out.println("List all segmentes success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return segmentes;
    }
}
