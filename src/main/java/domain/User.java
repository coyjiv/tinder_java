package domain;

public class User {
String avatarUrl;
String username;

public User(String avatarUrl, String username){
    this.avatarUrl = avatarUrl;
    this.username = username;
}

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
