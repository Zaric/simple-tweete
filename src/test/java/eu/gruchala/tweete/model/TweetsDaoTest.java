package eu.gruchala.tweete.model;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * <p/>
 *
 * @author lgr
 */
public class TweetsDaoTest {

    private TweetsDao tweetsDao;

    @BeforeMethod
    public void setUp() throws Exception {
        tweetsDao = new TweetsDao();
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void shouldGetTweets() throws Exception {
        //when
        List<String> tweets = tweetsDao.getTweets();

        //then
        Assert.assertNotNull(tweets);
        Assert.assertTrue(tweets.isEmpty());
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void shouldAddValidTweet() throws Exception {
        //when
        List<String> tweets = tweetsDao.addTweet("test");

        //then
        Assert.assertNotNull(tweets);
        Assert.assertFalse(tweets.isEmpty());
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void shouldNotAddInvalidTweet() throws Exception {
        //when
        List<String> tweets = tweetsDao.addTweet(null);

        //then
        Assert.assertNotNull(tweets);
        Assert.assertTrue(tweets.isEmpty());
    }
}
