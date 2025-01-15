
package com.mycompany.slb1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SLb1 {

    public static void main(String[] args) {
        List<Integer> num = Generate(10_000_000, 1, 100);


        long sum = Sum(num);
        System.out.println("Сума елементів: " + sum);

        double average = Average(num);
        System.out.println("Середнє значення: " + average);

        double stdDeviation = Deviation(num);
        System.out.println("Стандартне відхилення: " + stdDeviation);

        List<Integer> doubledList = Multiply(num);
        System.out.println("Перші 10 елементів, помножених на 2: " 
                           + doubledList.subList(0, 10));
        int size = doubledList.size();
        System.out.println("Останні 10 елементів, помножених на 2: " 
                           + doubledList.subList(size - 10, size));

        List<Integer> filteredList = Filter(num);
        System.out.println("Перші 10 відфільтрованих елементів: " 
                           + filteredList.subList(0, Math.min(10, filteredList.size())));
        int sizeF = filteredList.size();
        int fromIndex = Math.max(0, sizeF - 10); 
        System.out.println("Останні 10 відфільтрованих елементів: " 
                           + filteredList.subList(fromIndex, sizeF));
    }

    
    public static List<Integer> Generate(int size, int min, int max) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(max - min + 1) + min);
        }
        return list;
    }

    public static long Sum(List<Integer> num) {
        return num.parallelStream()
                  .mapToLong(Integer::longValue)
                  .sum();
    }

    public static double Average(List<Integer> num) {
        return num.parallelStream()
                  .mapToDouble(Integer::doubleValue)
                  .average()
                  .orElse(0.0);
    }

    public static double Deviation(List<Integer> num) {
        double mean = Average(num);

        double averageSquare = num.parallelStream()
                                  .mapToDouble(Integer::doubleValue)
                                  .map(x -> x * x)
                                  .average()
                                  .orElse(0.0);

        double variance = averageSquare - (mean * mean);
        return Math.sqrt(variance);
    }
    
    public static List<Integer> Multiply(List<Integer> num) {
        return num.parallelStream()
                  .map(i -> i * 2)
                  .collect(Collectors.toList());
    }

    public static List<Integer> Filter(List<Integer> num) {
        return num.parallelStream()
                  .filter(i -> i % 2 == 0 && i % 3 == 0)
                  .collect(Collectors.toList());
    }
}
