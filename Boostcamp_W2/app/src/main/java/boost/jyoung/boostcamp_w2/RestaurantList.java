package boost.jyoung.boostcamp_w2;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class RestaurantList {

    int itemImg;
    String itemTitle;
    String itemContent;
    int distance;
    int popular;
    int postdate;

    public RestaurantList(int itemImg, String itemTitle, String itemContent, int distance, int popular, int postdate) {
        this.itemImg = itemImg;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
        this.distance = distance;
        this.popular = popular;
        this.postdate = postdate;
    }

    public int getItemImg() {
        return itemImg;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemContent() {
        return itemContent;
    }

    public int getPostdate() {
        return postdate;
    }

    public int getDistance() {
        return distance;
    }

    public int getPopular() {
        return popular;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public void setPostdate(int postdate) {
        this.postdate = postdate;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }
}
