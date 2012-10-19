package eu.gruchala.tweete.controller;

import java.util.List;
import java.util.Map;

import eu.gruchala.tweete.model.Tweet;
import eu.gruchala.tweete.model.TweetsDao;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * <p/>
 *
 * @author lgr
 */
public class DefaultControllerTest {

    private TweetsDao tweetsDao;
    private DefaultController defaultController;
    private BindingResult bindingResult;
    private Tweet tweet;

    @BeforeMethod
    public void setUp() throws Exception {
        tweetsDao = new TweetsDao();
        defaultController = new DefaultController(tweetsDao);
        tweet = mock(Tweet.class);
        when(tweet.getValue()).thenReturn("something to say");
        bindingResult = mock(BindingResult.class);
    }

    @Test
    public void shouldProvideTweets() throws Exception {
        //when
        final List<String> tweets = defaultController.provideTweets();

        //then
        assertNotNull(tweets);
        assertTrue(tweets.isEmpty());
    }

    @Test
    public void shouldShowIndexPageWithModel() throws Exception {
        //given
        final ExtendedModelMap model = new ExtendedModelMap();

        //when
        final String index = defaultController.index(model);

        //then
        assertEquals(index, "index");
        final Map<String, Object> modelsMap = model.asMap();
        assertNotNull(modelsMap.get("tweet"));
        assertTrue(modelsMap.get("tweet") instanceof Tweet);
    }

    @Test
    public void shouldAddTweetWithoutError() throws Exception {
        //given
        when(bindingResult.hasErrors()).thenReturn(false);

        //when
        final String page = defaultController.saveTweet(tweet, bindingResult);

        //then
        assertNotNull(tweetsDao.getTweets());
        assertTrue(tweetsDao.getTweets().size() == 1);
        assertEquals(page, "redirect:/");
    }

    @Test
    public void shouldNotAddTweetWithError() throws Exception {
        //given
        when(bindingResult.hasErrors()).thenReturn(true);

        //when
        final String page = defaultController.saveTweet(tweet, bindingResult);

        //then
        assertNotNull(tweetsDao.getTweets());
        assertTrue(tweetsDao.getTweets().isEmpty());
        assertEquals(page, "index");
    }
}
