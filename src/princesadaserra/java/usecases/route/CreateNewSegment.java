package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Segment;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.SegmentRepository;
import princesadaserra.java.util.threading.Task;

public class CreateNewSegment extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Segment segment = null;

    public CreateNewSegment(PDSDatabaseConnectionPool connectionPool, Segment segment){

        this.connectionPool = connectionPool;
        this.segment = segment;
    }

    @Override
    protected Boolean execute(String useless) {

        SegmentRepository segmentRepository = null;
        try{

            segmentRepository = new SegmentRepository(connectionPool);
            segmentRepository.add(segment);
            System.out.println("Add segment success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
