package cpr.alghoritms;

import cpr.AbstractTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrecisionRecallTest extends AbstractTest {

    @Test
    public void precisionRecall1() throws IOException {
        Set<Pattern> golden = getGoldenParserTsv1().getGoldenClusters();
        Set<Set<String>> real = new HashSet<>();

        Set<String> c1 = new HashSet<>();
        Set<String> c2 = new HashSet<>();
        real.add(c1);
        real.add(c2);

        c1.add("1");
        c1.add("2");
        c2.add("3");

        PrecisionRecall precisionRecall = new PrecisionRecallClassic(real, golden);
        assertEquals(new Double(1), precisionRecall.getPrecision());
        assertEquals(new Double(1), precisionRecall.getRecall());
        assertEquals(new Double(1), precisionRecall.getFScore());
    }

    @Test
    public void precisionRecall2() throws IOException {
        Set<Pattern> golden = getGoldenParserTsv1().getGoldenClusters();
        Set<Set<String>> real = new HashSet<>();

        Set<String> c1 = new HashSet<>();
        real.add(c1);

        c1.add("1");
        c1.add("2");
        c1.add("3");

        PrecisionRecall precisionRecall = new PrecisionRecallClassic(real, golden);
        assertEquals(new Double(0.667), precisionRecall.getPrecision());
        assertEquals(new Double(1), precisionRecall.getRecall());
        assertEquals(new Double(0.8), precisionRecall.getFScore());
    }

}
