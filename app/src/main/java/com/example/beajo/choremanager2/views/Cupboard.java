package com.example.beajo.choremanager2.views;

/**
 * Created by beajo on 2017-11-29.
 */

public class Cupboard {
    private String _id;
    private String _cupboardname;

    public Cupboard() {
    }
    public Cupboard(String id, String cupboardname) {
        _id = id;
        _cupboardname = cupboardname;
    }
    public Cupboard(String cupboardname) {
        _cupboardname = cupboardname;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setCupboardName(String cupboardname) {
        _cupboardname = cupboardname;
    }
    public String getCupboardName() {
        return _cupboardname;
    }
}
