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
        return new InputStreamReader(AbstractTest.class.getResourceAsStream(path), "UTF-8");
    }
    
}
```

## Example of golden file
```csv
product 1.html|2.html
single  3.html|4.html
page    page-*.html|page/*.html
```
