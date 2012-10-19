package eu.gruchala.tweete.controller;

import java.util.List;

import javax.inject.Inject;

import eu.gruchala.tweete.model.Tweet;
import eu.gruchala.tweete.model.TweetsDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    private final static Logger L = LoggerFactory.getLogger(DefaultController.class);
    private TweetsDao tweetsDao;

    @Inject
    public DefaultController(final TweetsDao tweetsDao) {
        this.tweetsDao = tweetsDao;
    }

    @ModelAttribute("tweets")
    public List<String> provideTweets() {
        return tweetsDao.getTweets();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final Model model) {
        model.addAttribute("tweet", new Tweet());
        return "index";
    }

    @RequestMapping(value = "/saveTweet", method = RequestMethod.POST)
    public String saveTweet(@Validated @ModelAttribute("tweet") final Tweet tweet, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.");
            return "index";
        }
        tweetsDao.addTweet(tweet.getValue());
        L.info("Added tweet: " + tweet);
        return "redirect:/";
    }
}
