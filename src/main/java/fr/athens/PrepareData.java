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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Roman Smetana
 */
public class PrepareData {

    public static void filterFile(String filePath) throws IOException {
        List<List<String>> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonObject tweet = new JsonParser().parse(line).getAsJsonObject();

                JsonArray hashtagsElem = tweet.getAsJsonObject("entities").getAsJsonArray("hashtags");
                List<String> hashtags = new ArrayList<>(hashtagsElem.size());
                for (JsonElement hashtagElem : hashtagsElem) {
                    String hashtag = hashtagElem.getAsJsonObject().get("text").getAsString();
                    hashtags.add(hashtag);
                }
                Collections.sort(hashtags);
                if (!list.contains(hashtags)) {
                    list.add(hashtags);
                }
            }
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

        File file = new File(filePath + "-parsed");

        // if file doesnt exists, then create it
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

}
