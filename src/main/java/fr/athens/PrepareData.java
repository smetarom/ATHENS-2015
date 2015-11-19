package fr.athens;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman Smetana
 */
public class PrepareData {

    public static void filterFile(String directoryPath, String[] filePaths) throws IOException {
        List<List<String>> list = new LinkedList<>();

        for (String filePath : filePaths) {
            String line = null;
            try (BufferedReader br = new BufferedReader(new FileReader(directoryPath+"/"+filePath))) {
                while ((line = br.readLine()) != null) {
                    JsonObject tweet = new JsonParser().parse(line).getAsJsonObject();

                    JsonArray hashtagsElem = tweet.getAsJsonObject("entities").getAsJsonArray("hashtags");
                    Set<String> uniqueHashtags = new HashSet<>();
                    for (JsonElement hashtagElem : hashtagsElem) {
                        String hashtag = hashtagElem.getAsJsonObject().get("text").getAsString();
                        uniqueHashtags.add(hashtag.toLowerCase());
                    }
                    List<String> hashtags = new ArrayList<>(uniqueHashtags);
                    Collections.sort(hashtags);
                    if (!list.contains(hashtags)) {
                        list.add(hashtags);
                    }
                }
            } catch (Exception ex) {
//                System.err.println(line);
            }
            System.out.println("Parsed: " + filePath);
        }

        Map<String, Integer> map = new HashMap<>();
        for (List<String> strings : list) {
            for (int i = 0; i < strings.size(); i++) {
                for (int j = i + 1; j < strings.size(); j++) {
                    String key = strings.get(i) + " " + strings.get(j);
                    Integer weight = map.get(key);
                    if (weight == null) {
                        map.put(key, 1);
                    } else {
                        map.put(key, weight + 1);
                    }
                }
            }
        }

        File file = new File(directoryPath+"/graph");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue());
            bw.newLine();
        }
        bw.close();
    }

    public static void calcStats(String directoryPath, String[] filePaths, AlgorithmResult result) throws IOException {
        Set<Long> userIds = new HashSet<>();
        Set<Long> tweetIds = new HashSet<>();
        for (String filePath : filePaths) {
            String line = null;
            try (BufferedReader br = new BufferedReader(new FileReader(directoryPath+"/"+filePath))) {
                while ((line = br.readLine()) != null) {
                    JsonObject tweet = new JsonParser().parse(line).getAsJsonObject();

                    JsonArray hashtagsElem = tweet.getAsJsonObject("entities").getAsJsonArray("hashtags");
                    Set<String> uniqueHashtags = new HashSet<>();
                    for (JsonElement hashtagElem : hashtagsElem) {
                        String hashtag = hashtagElem.getAsJsonObject().get("text").getAsString();
                        uniqueHashtags.add(hashtag.toLowerCase());
                    }

                    boolean contributed = false;
                    for (String resultHashtag : result.getHashtags()) {
                        if (uniqueHashtags.contains(resultHashtag)) {
                            contributed = true;
                            break;
                        }
                    }

                    if (contributed) {
                        long userId = tweet.getAsJsonObject("user").get("id").getAsLong();
                        userIds.add(userId);
                        long tweetId = tweet.get("id").getAsLong();
                        tweetIds.add(tweetId);
                    }
                }
            } catch (Exception ex) {
//                System.err.println(line);
            }
            System.out.println("Stats calc: " + filePath);
        }
        result.setUniqueUsers(userIds.size());
        result.setUniqueTweets(tweetIds.size());
    }

    public static void printResultToFile(String folder, List<AlgorithmResult> results) throws IOException {
        File file = new File(folder+"/result-"+System.currentTimeMillis());

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (AlgorithmResult result : results) {
            bw.write(result.toString());
            bw.newLine();
        }
        bw.close();
    }

    public static void prepareHashtags(String directoryPath, String[] filePaths) throws IOException {
        File file = new File(directoryPath+"/stats");

        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (String filePath : filePaths) {
            String line = null;
            try (BufferedReader br = new BufferedReader(new FileReader(directoryPath+"/"+filePath))) {
                while ((line = br.readLine()) != null) {
                    JsonObject tweet = new JsonParser().parse(line).getAsJsonObject();

                    JsonArray hashtagsElem = tweet.getAsJsonObject("entities").getAsJsonArray("hashtags");
                    Set<String> uniqueHashtags = new HashSet<>();
                    for (JsonElement hashtagElem : hashtagsElem) {
                        String hashtag = hashtagElem.getAsJsonObject().get("text").getAsString();
                        uniqueHashtags.add(hashtag.toLowerCase());
                    }

                    JsonObject finalTweet = new JsonObject();
                    finalTweet.addProperty("userId", tweet.getAsJsonObject("user").get("id").getAsLong());
                    finalTweet.addProperty("tweetId", tweet.get("id").getAsLong());
                    JsonArray h = new JsonArray();
                    for (String uniqueHashtag : uniqueHashtags) {
                        h.add(uniqueHashtag);
                    }
                    finalTweet.add("hashtags", h);
                    bw.write(finalTweet.toString());
                    bw.newLine();
                }
            } catch (Exception ex) {
//                System.err.println(line);
            }
            System.out.println("Tweet prepare: " + filePath);
        }
        bw.close();
    }
}
