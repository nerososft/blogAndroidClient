package com.neroyang.leban.leban.Bean;

/**
 * Created by nero on 2016/3/26.
 */
public class PassageDetailBean {
    private  String  id;
    private String username;//'=>$user_model->user_name,
    private String title;//'=>$row['title'],
    private String content;//'=>$row['content'],
    private String tag;//' => $row['tagname'],
    private String up;//' => $row['up'],
    private String down;//'=>$row['down'],
    private String hot;//'=>$row['hot'],
    private String comment;//'=>$comment_model->all(),
    private String createtime;//' => $my_time->index()

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
