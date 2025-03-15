package ar.edu.unlp.info.oo2.repaso_ej1;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private String screenName;
	private List<Tweet> tweets;
	
	public Usuario(String screenName) {
		this.screenName = screenName;
		tweets = new LinkedList<Tweet>();
	}
	
	public String getScreenName() {
		return this.screenName;
	}
	
	public void tweet(String text) {
		if (text.length() > 1 && text.length() < 280) {
			Tweet tweet = new Tweet(text);
			this.tweets.add(tweet);
		}
	}
	
	public void retweet(Tweet tweet) {
		Retweet retweet = new Retweet(tweet);
		this.tweets.add(retweet);
	}
	
	public List<Tweet> getTweets() {
		return this.tweets;
	}
	

}
