package cpr.alghoritms;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PrecisionRecallClusters<T> implements PrecisionRecall {

    private final Set<Set<T>> founded;
    private final Set<Set<T>> golden;

    public PrecisionRecallClusters(Set<Set<T>> founded, Set<Pattern> golden) {
        this.founded = founded;

        Set<T> flatten = this.flattenSet(founded);

        Map<Pattern, Set<T>> mapGolden = new HashMap<>();

        flatten.forEach(element -> {
            Pattern p = golden
                .parallelStream()
                .filter(g -> g.matcher(element.toString()).matches())
                .findFirst()
                .get();
            mapGolden.putIfAbsent(p, new HashSet<>());
            mapGolden.get(p).add(element);
        });

        this.golden = new HashSet<>(mapGolden.values());
    }

    @Override
    public Double getRecall() {
        Double result = this.founded
            .parallelStream()
            .mapToDouble(r ->
                this.golden
                    .parallelStream()
                    .mapToDouble(g -> 1D * this.intersection(r, g).size() / g.size())
                    .max()
                    .getAsDouble()
            ).sum();

        return this.approximate(result/this.founded.size());
    }

    @Override
    public Double getPrecision() {
        Double result = this.founded
            .parallelStream()
            .mapToDouble(r ->
                this.golden
                    .parallelStream()
                    .mapToDouble(g -> 1D * this.intersection(r, g).size() / r.size())
                    .max()
                    .getAsDouble()
            ).sum();

        return this.approximate(result/this.founded.size());
    }

    @Override
    public Double getFScore() {
        Double P = this.getPrecision();
        Double R = this.getRecall();

        return this.approximate((2*P*R)/(P+R));
    }

    /**
     * Returns a flatten set.
     *
     * @param set
     * @return
     */
    private Set<T> flattenSet(Set<Set<T>> set) {
        return set
                .parallelStream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Returns the intersection between two set.
     *
     * @param set1
     * @param set2
     * @return
     */
    private Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>();
        result.addAll(set1);
        result.retainAll(set2);
        return result;
    }

}
