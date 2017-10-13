package cpr;

import cpr.parsers.GoldenParser;
import cpr.parsers.GolderParserCSV;
import cpr.parsers.GolderParserTSV;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class AbstractTest {

    protected static GoldenParser getGoldenParserTsv1() throws IOException {
        return new GolderParserTSV(getReader("/golden_test1.tsv"));
    }

    protected static GoldenParser getGoldenParserWrongTsv() throws IOException {
        return new GolderParserTSV(getReader("/wrong_golden_test1.tsv"));
    }

    protected static GoldenParser getGoldenParserCsv1() throws IOException {
        return new GolderParserCSV(getReader("/golden_test1.csv"));
    }

    protected static Reader getReader(String path) throws UnsupportedEncodingException {
        return new InputStreamReader(AbstractTest.class.getResourceAsStream(path), "UTF-8");
    }

}
