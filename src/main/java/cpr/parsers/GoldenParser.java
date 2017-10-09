package cpr.parsers;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public interface GoldenParser {

    Map<String, Pattern> getGolden();
    Set<Pattern> getGoldenClusters();

}
