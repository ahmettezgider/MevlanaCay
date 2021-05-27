package _DENEME._otherpages;

import org.testng.annotations.Test;
import utils.Locators;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;


public class Performans implements Locators {

    public static void main(String[] args){
        long startDate;
        long endDate;

        LinkedHashMap<Thread, LinkedHashMap<String, Long>> threads = new LinkedHashMap<>();

        for (int i = 0; i < 3; i++) {
            LinkedHashMap<String, Long> time = new LinkedHashMap<>();
            startDate = System.nanoTime();
            Thread t = new Thread(
                    () -> {

                        open(urlDe);

                    }
                );
            endDate = System.nanoTime();
            time.put("start", startDate);
            time.put("end", endDate);
            time.put("diff", endDate-startDate);
            threads.put(t, time);
        }

        for (Map.Entry<Thread, LinkedHashMap<String, Long>> entry : threads.entrySet()) {
            entry.getKey().start();
            System.out.println(entry.getKey() + " , " + entry.getValue());
        }

    }

    @Test
    public void test(){
        long startDate;
        long endDate;

        LinkedHashMap<Thread, LinkedHashMap<String, Long>> threads = new LinkedHashMap<>();

        for (int i = 0; i < 3; i++) {
            LinkedHashMap<String, Long> time = new LinkedHashMap<>();
            startDate = System.nanoTime();
            Thread t = new Thread(
                    () -> {

                        open(urlDe);

                    }
            );
            endDate = System.nanoTime();
            time.put("start", startDate);
            time.put("end", endDate);
            time.put("diff", endDate-startDate);
            threads.put(t, time);
        }

        for (Map.Entry<Thread, LinkedHashMap<String, Long>> entry : threads.entrySet()) {
            entry.getKey().start();
            System.out.println(entry.getKey() + " , " + entry.getValue());
        }

    }

}