/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreamAPI_Exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Eranda
 */
public class Tester {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("Kamal", 20);
                put("Nimal", 61);
                put("Saman", 90);
                put("Sunil", 95);
                put("Amal", 40);
            }
        };

        System.out.println(map.entrySet()
                .stream()
                .filter(e -> e.getValue() > 60)
                .sorted(Comparator.comparing(e -> e.getKey()))
                .collect(Collectors.toList()));
    }
}
