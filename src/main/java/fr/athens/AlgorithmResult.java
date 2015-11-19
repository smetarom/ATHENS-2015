package fr.athens;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Roman Smetana
 */
public class AlgorithmResult {
    private double density;
    private long uniqueUsers;
    private long uniqueTweets;
    private Set<String> hashtags = new HashSet<>();


    public void addHashtag(String hashtag) {
        hashtags.add(hashtag);
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public long getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(long uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    public long getUniqueTweets() {
        return uniqueTweets;
    }

    public void setUniqueTweets(long uniqueTweets) {
        this.uniqueTweets = uniqueTweets;
    }

    @Override
    public String toString() {
        return "AlgorithmResult{" +
                "density=" + density +
                ", uniqueUsers=" + uniqueUsers +
                ", uniqueTweets=" + uniqueTweets +
                ", hashtags=" + hashtags +
                '}';
    }
}
