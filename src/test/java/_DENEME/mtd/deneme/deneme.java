package _DENEME.mtd.deneme;

import org.testng.annotations.Test;
import utils.Locators;
import utils.MyMethods;

import java.util.HashMap;
import java.util.Map;

public class deneme extends MyMethods implements Locators {

    @Test
    public void ddd(){

        Map<String, String> vals = new HashMap<>();
        vals.put("a", "1");
        vals.put("b", "2");
        vals.put("c", "q");
        vals.put("d", "a");

        writeToExcel("fn", "bilgiler", vals);
        writeToExcel("fn", "bilgiler", vals);
    }




}
