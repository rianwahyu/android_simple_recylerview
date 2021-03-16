package com.bibit.project.simplerecylerview;

public class ModelItem {
    String itemNumber, itemDescription, sizeandcolor, um, um2, itemcategories, displayname, descriptionname, size,
    realprice, endprice, source;

    public ModelItem(String itemNumber, String itemDescription, String sizeandcolor, String um,
                     String um2, String itemcategories, String displayname, String descriptionname,
                     String size, String realprice, String endprice, String source) {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.sizeandcolor = sizeandcolor;
        this.um = um;
        this.um2 = um2;
        this.itemcategories = itemcategories;
        this.displayname = displayname;
        this.descriptionname = descriptionname;
        this.size = size;
        this.realprice = realprice;
        this.endprice = endprice;
        this.source = source;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getSizeandcolor() {
        return sizeandcolor;
    }

    public String getUm() {
        return um;
    }

    public String getUm2() {
        return um2;
    }

    public String getItemcategories() {
        return itemcategories;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getDescriptionname() {
        return descriptionname;
    }

    public String getSize() {
        return size;
    }

    public String getRealprice() {
        return realprice;
    }

    public String getEndprice() {
        return endprice;
    }

    public String getSource() {
        return source;
    }
}
