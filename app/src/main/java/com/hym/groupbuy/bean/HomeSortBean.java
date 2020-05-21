package com.hym.groupbuy.bean;

/**
 * 这个是homefragment的商品分类的bean
 */
public class HomeSortBean {
    String iconName;
    int iconID;

    public HomeSortBean(String iconName, int iconID) {
        this.iconName = iconName;
        this.iconID = iconID;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
}
