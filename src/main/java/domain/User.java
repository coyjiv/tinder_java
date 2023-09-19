package domain;

public class User {
    private final Long userId;
    private final String username;
    private String password;
    private final String profilePhoto;

    public User(Long userId, String username, String password, String profilePhoto) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public User(long userId, String username, String profilePhoto) {
        this.userId = userId;
        this.username = username;
        this.profilePhoto = profilePhoto;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getProfilePhoto() != null ? getProfilePhoto().equals(user.getProfilePhoto()) : user.getProfilePhoto() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getProfilePhoto() != null ? getProfilePhoto().hashCode() : 0);
        return result;
    }
}
