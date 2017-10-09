package cpr.parsers;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.regex.Pattern;

public class GolderParserCSV implements GoldenParser {

    private final Reader source;
    private Map<String, Pattern> golden;

    public GolderParserCSV(Reader source) throws IOException {
        this.source = source;
    }

    private void loadGolden() {
        golden = new HashMap<>();
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
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
