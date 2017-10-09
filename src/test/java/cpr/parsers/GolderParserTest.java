package cpr.parsers;

import cpr.AbstractTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GolderParserTest extends AbstractTest {

    @Test
    public void parseGoldenTsv1() throws IOException {
        GoldenParser parser = getGoldenParserTsv1();
        Map<String, Pattern> golden = parser.getGolden();
        Set<Pattern> goldenClusters = parser.getGoldenClusters();
        assertEquals(3, golden.size());
        assertEquals(3, goldenClusters.size());
    }

    @Test
    public void parseGoldenCsv1() throws IOException {
        GoldenParser parser = getGoldenParserCsv1();
        Map<String, Pattern> golden = parser.getGolden();
        Set<Pattern> goldenClusters = parser.getGoldenClusters();
        assertEquals(3, golden.size());
        assertEquals(3, goldenClusters.size());
    }

}
