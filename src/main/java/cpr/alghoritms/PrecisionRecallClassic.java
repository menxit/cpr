package cpr.alghoritms;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PrecisionRecallClassic implements PrecisionRecall {

    private final Set<String> flatten;
    private final Set<Set<String>> real;
    private final Set<Set<String>> golden;

    public PrecisionRecallClassic(Set<Set<String>> real, Set<Pattern> golden) {
        this.flatten = this.flattenSet(real);
        this.real = real;
        Map<Pattern, Set<String>> mapGolden = new HashMap<>();

        this.flatten.forEach(element -> {
            Pattern p = golden
                .parallelStream()
                .filter(g -> g.matcher(element).matches())
                .findFirst()
                .get();
            mapGolden.putIfAbsent(p, new HashSet<>());
            mapGolden.get(p).add(element);
        });

        this.golden = new HashSet<>(mapGolden.values());
    }

    private Set<String> flattenSet(Set<Set<String>> set) {
        return set
            .parallelStream()
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
    }

    private Set<String> intersection(Set<String> r, Set<String> g) {
        Set<String> result = new HashSet<>();
        result.addAll(r);
        result.retainAll(g);
        return result;
    }

    private Double approximate(Double N) {
        return Math.round(N*1000D)/1000D;
    }

    @Override
    public Double getRecall() {
        Double result = this.real
            .parallelStream()
            .mapToDouble(r ->
                this.golden
                    .parallelStream()
                    .mapToDouble(g -> 1D * this.intersection(r, g).size() / g.size())
                    .max()
                    .getAsDouble()
            ).sum();

        return this.approximate(result/this.real.size());
    }

    @Override
    public Double getPrecision() {
        Double result = this.real
            .parallelStream()
            .mapToDouble(r ->
                this.golden
                    .parallelStream()
                    .mapToDouble(g -> 1D * this.intersection(r, g).size() / r.size())
                    .max()
                    .getAsDouble()
            ).sum();

        return this.approximate(result/this.real.size());
    }

    @Override
    public Double getFScore() {
        Double P = this.getPrecision();
        Double R = this.getRecall();

        return this.approximate((2*P*R)/(P+R));
    }
}
