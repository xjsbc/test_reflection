package test;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"all"})
public class Test01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> collect = list.stream().filter(l -> l.equals(3)).collect(Collectors.toList());
        System.out.println(collect.size());
    }
}