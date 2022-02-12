package edu.neu.madcourse.numad22sp_yizhang;

public class ItemCard {

    private final String itemName;
    private final String itemDesc;

    //Constructor
    public ItemCard(String itemName, String itemDesc) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;

    }

    //Getters for the imageSource, itemName and itemDesc
    public String getItemDesc() {
        return itemDesc;
    }

    public String getItemName() {
        return itemName;
    }
}