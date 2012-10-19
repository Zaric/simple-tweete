package eu.gruchala.tweete.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

/** Dao for tweets. Contains very simple application cache for {@link Tweet} objects. */
@Repository
public class TweetsDao {

    private volatile List<String> tweetsCache;

    /**
     * Provides list of added tweets.
     *
     * @return list of tweets as unmodifiable new copy of that list.
     */
    public List<String> getTweets() {
        return Collections.unmodifiableList(provideTweets());
    }

    /**
     * Adds tweet to the cache.
     *
     * @param tweet to be added
     * @return updated list of tweets as unmodifiable new copy of that list.
     */
    public List<String> addTweet(final String tweet) {
        tweetsCache = provideTweets();
        if (tweet != null) {
            tweetsCache.add(tweet);
        }
        return Collections.unmodifiableList(tweetsCache);
    }

    private List<String> provideTweets() {
        List<String> result = tweetsCache;//on some VMs this speeds up to 25%
        if (result == null) {
            synchronized (this) {
                result = tweetsCache;
                if (result == null) {
                    tweetsCache = result = new ArrayList<String>();
                }
            }
        }
        return result;
    }
}
