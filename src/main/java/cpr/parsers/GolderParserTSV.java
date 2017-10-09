package cpr.parsers;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GolderParserTSV implements GoldenParser {

    private final Reader source;
    private Map<String, Pattern> golden;

    public GolderParserTSV(Reader source) throws IOException {
        this.source = source;
    }

    private void loadGolden() {
        golden = new HashMap<>();
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);
        List<String[]> rows = parser.parseAll(source);

        rows.forEach(l -> {
            String pageClassName = l[0];
            Pattern regex = Pattern.compile(l[1]);
            this.golden.put(pageClassName, regex);
        });
    }

    @Override
    public Map<String, Pattern> getGolden() {
        if(golden == null) {
            loadGolden();
        }
        return golden;
    }

    @Override
    public Set<Pattern> getGoldenClusters() {
        return new HashSet<>(getGolden().values());
    }

}
