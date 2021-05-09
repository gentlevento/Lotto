package application.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Pair<K, V> {

    K word;
    V weight;

    public Pair(K word, V weight) {
        this.word = word;
        this.weight = weight;
    }
}

class WeightedRandom {

    private final List<Pair<Integer, Double>> candidates;

    public WeightedRandom(List<Pair<Integer, Float>> target) {
        // 1. 총 가중치 합 계산
        double totalWeight = 0;
        for (Pair<Integer, Float> pair : target) {
            totalWeight += pair.weight;
        }

        // 2. 주어진 가중치를 백분율로 치환 (가중치 / 총 가중치)
        List<Pair<Integer, Double>> candidates = new ArrayList<>();
        for (Pair<Integer, Float> pair : target) {
            candidates.add(new Pair<>(pair.word, pair.weight / totalWeight));
        }

        // 3. 가중치의 오름차순으로 정렬
        candidates.sort(Comparator.comparingDouble(p -> p.weight));
        this.candidates = candidates;
    }

    public Integer getRandom() {
        // 1. 랜덤 기준점 설정
        final double pivot = Math.random();

        // 2. 가중치의 오름차순으로 원소들을 순회하며 가중치를 누적
        double acc = 0;
        for (Pair<Integer, Double> pair : candidates) {
            acc += pair.weight;

            // 3. 누적 가중치 값이 기준점 이상이면 종료
            if (pivot <= acc) {
                return pair.word;
            }
        }

        return null;
    }
}