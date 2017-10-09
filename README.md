# Precision & Recall of clusters

## How to parse a golden file (TSV)?
```java
class Example {
    
    public void main(String args[]) {
        GoldenParse golden = new GolderParserTSV(getReader("/golden_test1.tsv"));
        Map<String, Pattern> golden = g.getGolden();
        Set<Pattern> goldenClusters = g.getGoldenClusters();
    }
    
    public static Reader s(String path) throws UnsupportedEncodingException {
        return new InputStreamReader(Example.class.getResourceAsStream(path), "UTF-8");
    }
    
}
```

### Example of golden file
```csv
product 1.html|2.html
single  3.html|4.html
page    page-*.html|page/*.html
```

## How to calculate precision, recall and F1
```java
class Example {
    
    public void main(String args[]) {
        GoldenParse getGoldenParserTsv1 = new GolderParserTSV(getReader("/golden_test1.tsv"));
        Set<Pattern> golden = getGoldenParserTsv1.getGoldenClusters();
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
    
    public static Reader s(String path) throws UnsupportedEncodingException {
        return new InputStreamReader(Example.class.getResourceAsStream(path), "UTF-8");
    }
    
}