package fr.athens;

import java.io.IOException;
import java.util.List;

/**
 * @author Roman Smetana
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String[] newyorks = {"NewYork-2015-2-23", "NewYork-2015-2-24", "NewYork-2015-2-25", "NewYork-2015-2-26"};

        String[] oscars = {"Oscars-2015-2-23", "Oscars-2015-2-24", "Oscars-2015-2-25", "Oscars-2015-2-26"};

        String[] paris1 = {"Paris-2015-1-1","Paris-2015-1-2","Paris-2015-1-3","Paris-2015-1-10","Paris-2015-1-11","Paris-2015-1-12","Paris-2015-1-13","Paris-2015-1-14","Paris-2015-1-15","Paris-2015-1-16","Paris-2015-1-17","Paris-2015-1-18","Paris-2015-1-19","Paris-2015-1-20","Paris-2015-1-21","Paris-2015-1-22","Paris-2015-1-23","Paris-2015-1-24","Paris-2015-1-25","Paris-2015-1-26","Paris-2015-1-27","Paris-2015-1-28","Paris-2015-1-29","Paris-2015-1-30"};

        String[] paris2 = {"Paris-2015-2-1", "Paris-2015-2-10", "Paris-2015-2-11", "Paris-2015-2-12", "Paris-2015-2-13", "Paris-2015-2-14", "Paris-2015-2-15",  "Paris-2015-2-16",  "Paris-2015-2-17"};

        // Test
        processTopic("/home/smetana/Hadoop/athensDataTwitter/example", new String[] {"NewYork-2015-2-23"});

//        processTopic("/home/smetana/Hadoop/athensDataTwitter/NewYorkOneWeek", newyorks);
//        processTopic("/home/smetana/Hadoop/athensDataTwitter/ParisJanuary/ParisSearchJan", paris1);
//        processTopic("/home/smetana/Hadoop/athensDataTwitter/ParisFebruary/ParisSearchFeb", paris2);
//        processTopic("/home/smetana/Hadoop/athensDataTwitter/Oscars", oscars);
    }

    private static void processTopic(String folder, String[] files) throws IOException {
        PrepareData.prepareHashtags(folder, files);

//        PrepareData.filterFile(folder, files);
//        List<AlgorithmResult> results= Algorithm.denseGraph(folder + "/graph");
//        for (AlgorithmResult result : results) {
//            PrepareData.calcStats(folder, files, result);
//        }
//        PrepareData.printResultToFile(folder, results);
//        for (AlgorithmResult result : results) {
//            System.out.println(result);
//        }
    }
}
