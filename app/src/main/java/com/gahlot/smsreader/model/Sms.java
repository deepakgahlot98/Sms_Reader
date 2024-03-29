package com.gahlot.smsreader.model;


public class Sms{
    private String _id;
    private String _address;
    private String _msg;
    private String _readState; //"0" for have not read sms and "1" for have read sms
    private String _time;
    private String _folderName;
    private boolean isSubItem;

    public static Sms createRow(String id,String address,String msg, String time) {
        Sms ret = new Sms();
        ret._id = id;
        ret._address = address;
        ret._msg = msg;
        ret._time = time;
        ret.isSubItem = true;
        return ret;
    }

    public static Sms createSection(String time) {
        Sms ret = new Sms();
        ret._time = time;
        ret.isSubItem = false;
        return ret;
    }

    public String getId(){
        return _id;
    }
    public String getAddress(){
        return _address;
    }
    public String getMsg(){
        return _msg;
    }
    public String getReadState(){
        return _readState;
    }
    public String getTime(){
        return _time;
    }
    public String getFolderName(){
        return _folderName;
    }

    public boolean isSubItem() {
        return isSubItem;
    }

    public void setSubItem(Boolean subItem) {
        isSubItem = subItem;
    }

    public void setId(String id){
        _id = id;
    }
    public void setAddress(String address){
        _address = address;
    }
    public void setMsg(String msg){
        _msg = msg;
    }
    public void setReadState(String readState){
        _readState = readState;
    }
    public void setTime(String time){
        _time = time;
    }
    public void setFolderName(String folderName){
        _folderName = folderName;
    }

}
