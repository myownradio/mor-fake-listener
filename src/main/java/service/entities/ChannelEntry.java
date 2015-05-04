package service.entities;

/**
 * Created by Roman on 04.05.2015.
 */
public class ChannelEntry {
    private int sid;
    private int uid;
    private String name;
    private String permalink;
    private String info;
    private String hashtags;
    private String access;
    private int status;
    private String cover;
    private String coverBackground;
    private int created;
    private int bookmarksCount;
    private int listenersCount;
    private int isFeatured;
    private int playbacks;
    private int bookmarked;
    private String nowPlaying;
    private int timeLeft;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverBackground() {
        return coverBackground;
    }

    public void setCoverBackground(String coverBackground) {
        this.coverBackground = coverBackground;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getBookmarksCount() {
        return bookmarksCount;
    }

    public void setBookmarksCount(int bookmarks_count) {
        this.bookmarksCount = bookmarks_count;
    }

    public int getListenersCount() {
        return listenersCount;
    }

    public void setListenersCount(int listeners_count) {
        this.listenersCount = listeners_count;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public int getPlaybacks() {
        return playbacks;
    }

    public void setPlaybacks(int playbacks) {
        this.playbacks = playbacks;
    }

    public int getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(int bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(String nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
