package com.example.cory.feedthekitty;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by Akshay on 12/4/2017.
 */

@IgnoreExtraProperties
public class Event {

    public String eid;
    public String desc;
    public String name;
    public String owner;
    public boolean visibility;
    public Map<String, String>	 timestamp;
    public List<User> members;
    public Map<String, Integer> expenses;

    public Event(){

    }

    public Event(String eid, String desc, String name, String owner, boolean visibility, Map<String, String>	 timestamp, List<User> members, Map<String, Integer> expenses){
        this.eid = eid;
        this.desc = desc;
        this.name = name;
        this.owner = owner;
        this.visibility = visibility;
        this.timestamp = timestamp;
        this.members = members;
        this.expenses = expenses;
    }
}
