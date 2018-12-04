package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Segment;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.SegmentRepository;
import princesadaserra.java.util.threading.Task;

public class GetSegmentBySegmentId extends Task<String, Segment, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetSegmentBySegmentId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Segment execute(String useless){

        SegmentRepository segmentRepository = null;
        Segment segment = null;

        try{

            segmentRepository = new SegmentRepository(connectionPool);
            segment = new Segment();
            segment = segmentRepository.find(key);
            System.out.println("Find segment by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return segment;
    }
}
