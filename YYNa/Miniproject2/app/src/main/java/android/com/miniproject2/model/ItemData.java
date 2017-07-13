package android.com.miniproject2.model;

/**
 * Created by nasos on 2017-07-13.
 */

public class ItemData {
    String name;
    int img;
    String description;
    int popularity;
    int distance;
    int check;

    public ItemData(String name, int img, String description, int popularity, int distance, int check) {
        this.name = name;
        this.img = img;
        this.description = description;
        this.popularity = popularity;
        this.distance = distance;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
