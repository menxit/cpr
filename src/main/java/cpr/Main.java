package cpr;

import cpr.alghoritms.PrecisionRecall;
import cpr.alghoritms.PrecisionRecallClassic;
import cpr.parsers.GolderParserTSV;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        Set<Set<String>> c = Main.getClusterOfClusters();

        Set<Pattern> regexes = (new GolderParserTSV(getReader("/golden.tsv"))).getGoldenClusters();

        PrecisionRecall pr = new PrecisionRecallClassic(c, regexes);

        System.out.println("Precision: " + pr.getPrecision());
        System.out.println("Recall: " + pr.getRecall());
        System.out.println("F1 Measure: " + pr.getFScore());
    }

    private static Set<Set<String>> getClusterOfClusters() {
        Set<Set<String>> c = new HashSet<>();

        Set<String> c1 = new HashSet<>();
        Set<String> c2 = new HashSet<>();
        c1.add("1");
        c1.add("2");
        c2.add("3");

        c.add(c1);
        c.add(c2);

        return c;
    }

    private static Reader getReader(String path) throws UnsupportedEncodingException {
        return new InputStreamReader(GolderParserTSV.class.getResourceAsStream(path), "UTF-8");
    }
}
