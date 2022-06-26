package com.asuni.shortvideos.models;

public class VideoDataModel {

    private int tp;
    private int linked;
    private int isBlock;
    private int score;
    private int id;
    private int __v;


    private String fbId;
    private String city;
    private String _id;
    private String uid;
    private String description;
    private String status;
    private String video;
    private String gif;
    private String thum;

    private String created;

    private UserInfoModel userInfoModel;
    private CountModel countModel;
    private SoundModel soundModel;

    public VideoDataModel ( ){
        userInfoModel=new UserInfoModel();
        countModel=new CountModel();
        soundModel=new SoundModel();
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getCreated() {
        return created;
    }

    public int get__v() {
        return __v;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountModel(CountModel countModel) {
        this.countModel = countModel;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }


    public void setLinked(int linked) {
        this.linked = linked;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSoundModel(SoundModel soundModel) {
        this.soundModel = soundModel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String get_id() {
        return _id;
    }

    public String getThum() {
        return thum;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public CountModel getCountModel() {
        return countModel;
    }

    public String getFbId() {
        return fbId;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public int getLinked() {
        return linked;
    }

    public int getScore() {
        return score;
    }

    public int getTp() {
        return tp;
    }

    public SoundModel getSoundModel() {
        return soundModel;
    }

    public String getCity() {
        return city;
    }

    public String getGif() {
        return gif;
    }

    public String getStatus() {
        return status;
    }

    public String getUid() {
        return uid;
    }

    public String getVideo() {
        return video;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }
}
