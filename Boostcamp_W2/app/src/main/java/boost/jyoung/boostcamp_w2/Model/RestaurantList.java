package boost.jyoung.boostcamp_w2.Model;

/**
 * Created by jyoung on 2017. 7. 13..
 */

public class RestaurantList {

    public int itemImg;
    public String itemTitle;
    public String itemContent;
    public int distance;
    public int popular;
    public int postdate;
    public int check_state;

    public RestaurantList() {
    }

    public RestaurantList(int itemImg, String itemTitle, String itemContent, int distance, int popular, int postdate, int check_state) {
        this.itemImg = itemImg;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
        this.distance = distance;
        this.popular = popular;
        this.postdate = postdate;
        this.check_state = check_state;
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

    public int getCheck_state() {
        return check_state;
    }

    public void setCheck_state(int check_state) {
        this.check_state = check_state;
    }

}
