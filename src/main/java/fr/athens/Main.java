package fr.athens;

import java.io.IOException;

/**
 * @author Roman Smetana
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        PrepareData.filterFile("/home/smetana/Hadoop/athensDataTwitter/example.txt");
//        String[] newyorks = {"NewYork-2015-2-23", "NewYork-2015-2-24", "NewYork-2015-2-25", "NewYork-2015-2-26"};
//        String[] newyorks = {"NewYork-2015-2-23"};
//        PrepareData.filterFile("/home/smetana/Hadoop/athensDataTwitter/NewYorkOneWeek", newyorks);

//        String[] oscars = {"Oscars-2015-2-23", "Oscars-2015-2-24", "Oscars-2015-2-25", "Oscars-2015-2-26"};
//        PrepareData.filterFile("/home/smetana/Hadoop/athensDataTwitter/Oscars", oscars);

//        String[] paris1 = {"Paris-2015-1-1","Paris-2015-1-2","Paris-2015-1-3","Paris-2015-1-10","Paris-2015-1-11","Paris-2015-1-12","Paris-2015-1-13","Paris-2015-1-14","Paris-2015-1-15","Paris-2015-1-16","Paris-2015-1-17","Paris-2015-1-18","Paris-2015-1-19","Paris-2015-1-20","Paris-2015-1-21","Paris-2015-1-22","Paris-2015-1-23","Paris-2015-1-24","Paris-2015-1-25","Paris-2015-1-26","Paris-2015-1-27","Paris-2015-1-28","Paris-2015-1-29","Paris-2015-1-30"};
//        PrepareData.filterFile("/home/smetana/Hadoop/athensDataTwitter/ParisJanuary/ParisSearchJan", paris1);

//        String[] paris2 = {"Paris-2015-2-1", "Paris-2015-2-10", "Paris-2015-2-11", "Paris-2015-2-12", "Paris-2015-2-13", "Paris-2015-2-14", "Paris-2015-2-15",  "Paris-2015-2-16",  "Paris-2015-2-17"};
//        PrepareData.filterFile("/home/smetana/Hadoop/athensDataTwitter/ParisFebruary/ParisSearchFeb", paris2);

//        Algorithm.denseGraph("/home/smetana/Hadoop/athensDataTwitter/example2.txt");
//        Algorithm.denseGraph("/home/smetana/Hadoop/athensDataTwitter/NewYorkOneWeek/graph");
        Algorithm.denseGraph("/home/smetana/Hadoop/athensDataTwitter/Oscars/graph");
//        Algorithm.denseGraph("/home/smetana/Hadoop/athensDataTwitter/ParisJanuary/ParisSearchJan/graph");
//        Algorithm.denseGraph("/home/smetana/Hadoop/athensDataTwitter/ParisFebruary/ParisSearchFeb/graph");

    }
}
