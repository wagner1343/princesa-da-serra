package tests.core.route;

import org.junit.Assert;
import org.junit.Test;
import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Segment;

public class SegmentTest {

    @Test
    public void TestConnectsTo(){
        City a = new City("Guarapuava");
        City b = new City("Curitiba");
        City c = new City("Ponta Grossa");

        Segment segmentAb = new Segment(a, b, 0, 0);
        Segment segmentBc = new Segment(b, c, 0, 0);

        Assert.assertTrue("connectsTo() is broken", segmentAb.connectsTo(segmentBc));
    }
}
