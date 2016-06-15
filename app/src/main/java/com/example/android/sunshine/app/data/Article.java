package com.example.android.sunshine.app.data;

import android.text.Html;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 5/28/16.
 */

public class Article {
    private static final String IMAGE_BASE_URL = "http://disciplestoday.org/";
    public static final String DEFAULT_IMAGE_URL = "https://pbs.twimg.com/profile_images/186752127/DToday_logo_Gradient_Orange_400x400.jpg";
    public static final String TRACK_TYPE_ARTICLE="article";


    private Long _id; // for db/cupboard.
    private String id;
    private String title;
    private String imageLink;

    public String getLink() {
        return link;
    }

    private String link;

    private String fullText;
    private String author;
    private String summary; //or description, byline, etc.

    // zero arg constructor for sqllite/cupboard?
    public Article() {

    }

    private Article(String id, String title, String imageLink, String author, String summary, String fullText, String link) {
        this.id = id;
        this.title = title;
        this.imageLink = imageLink;
        this.fullText = fullText.replace("images/", IMAGE_BASE_URL + "/images/");;
        this.author = author;
        this.summary = summary;
        this.link = link;
    }

    //TODO: remove, for testing
    public Article(String title) {

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }





    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    /**
     * Given: Extra fields are title/description/image as of May 28,2016
     * They have name='title' etc,but is that better?
     * Waiting for API.
     * @param item
     * @return
     */
    public static Article newArticle(Item item) {
        String author = item.getCreated_by_alias();
        if ((item.getExtraFields() == null) || item.getExtraFields().size() == 0) {
            return new Article(item.getId(), item.getTitle(), item.getImageUrl(), author,
                    item.getIntroText(), item.getFulltext(), item.getLink());
        }

        String title = item.getExtraFields().get(0).getValue();
        String description = item.getExtraFields().get(1).getValue();
        String image = item.getImageUrl();

        return new Article(item.getId(), title, image, author, description, item.getFulltext(), item.getLink());
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDetailImageLink() {
        String s1 = fullText.replace("/images", IMAGE_BASE_URL + "images");
        if (s1.contains(imageLink)) {
            return "duplicate_image:'" + imageLink + "'";
        } else {
            return imageLink;
        }
    }

    public static List<Article> getArticles(Feed feed) {
        List<Article> articles = new ArrayList<>();
        if (feed.getItems() == null) {
            return null;
        }

        for (Item item : feed.getItems()) {
            articles.add(newArticle(item));
        }
        return  articles;
    }




}
