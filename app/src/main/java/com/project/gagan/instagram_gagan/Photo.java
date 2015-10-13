package com.project.gagan.instagram_gagan;

/**
 * Created by Gagan on 05-Oct-15.
 */
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Photo
 */

@ParseClassName("Photo")
public class Photo extends ParseObject {

    public Photo() {
        // A default constructor is required.
    }

    public ParseFile getImage() {
        return getParseFile("image");
    }

    public void setImage(ParseFile file) {
        put("image", file);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public ParseFile getThumbnail() {
        return getParseFile("thumbnail");
    }

    public void setThumbnail(ParseFile file) {
        put("thumbnail", file);
    }
    public int getLikeCount()
    {
        return getInt("likeCount");
    }
    public void setLikeCount(int i)
    {
        put("likeCount",i);
    }

}