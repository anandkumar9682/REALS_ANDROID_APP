package com.asuni.shortvideos.models;

public class SoundModel {

    private int id;
    private String soundName;
    private String description;
    private String thum;
    private String section;
    private String _id;
    private String created;

    private AudioPathModel audioPathModel;

    public SoundModel( ){
        audioPathModel=new AudioPathModel();
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setThum(String thum) {
        this.thum = thum;
    }

    public String getThum() {
        return thum;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String get_id() {
        return _id;
    }

    public String getSection() {
        return section;
    }

    public void setAudioPathModel(AudioPathModel audioPathModel) {
        this.audioPathModel = audioPathModel;
    }

    public AudioPathModel getAudioPathModel() {
        return audioPathModel;
    }
}
