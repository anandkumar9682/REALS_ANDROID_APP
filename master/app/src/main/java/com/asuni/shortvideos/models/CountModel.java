package com.asuni.shortvideos.models;

public class CountModel {

    private int likeCount;
    private int videoCommentCount;
    private int view;
    private String _id;

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getView() {
        return view;
    }

    public void setVideoCommentCount(int videoCommentCount) {
        this.videoCommentCount = videoCommentCount;
    }

    public int getVideoCommentCount() {
        return videoCommentCount;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }
}
