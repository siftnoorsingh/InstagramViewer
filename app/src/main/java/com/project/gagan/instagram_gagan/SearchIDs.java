package com.project.gagan.instagram_gagan;

import java.util.ArrayList;

/**
 * Created by Flin on 10/10/2015.
 */
public class SearchIDs {
    String s;
    ArrayList Ids;
    public SearchIDs(){
        this.s="";
        this.Ids = new ArrayList();
    }

    public ArrayList getId(){

//        for(int i = 0; i<Ids.size();i++){
//            s=(String)Ids.get(i);
//        }
//        Ids.toString();

        return Ids;
    }
    public void setId(String id){

        Ids.add(id);



    }
}
