package com.project.gagan.instagram_gagan;

import java.util.ArrayList;

/**
 * Created by Flin on 10/10/2015.
 * Global space to store searched IDs, this class is not used in the final version of the app
 */
public class SearchIDs {
    String s;
    ArrayList Ids;

    public SearchIDs() {
        this.s = "";
        this.Ids = new ArrayList();
    }

    public ArrayList getId() {

        return Ids;
    }

    public void setId(String id) {

        Ids.add(id);


    }
}
