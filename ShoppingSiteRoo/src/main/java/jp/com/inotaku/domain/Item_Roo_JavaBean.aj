// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.domain;

import java.util.Set;
import jp.com.inotaku.domain.Item;
import jp.com.inotaku.domain.SaleDetail;

privileged aspect Item_Roo_JavaBean {
    
    public long Item.getItemId() {
        return this.itemId;
    }
    
    public void Item.setItemId(long itemId) {
        this.itemId = itemId;
    }
    
    public String Item.getItemName() {
        return this.itemName;
    }
    
    public void Item.setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public int Item.getPrice() {
        return this.price;
    }
    
    public void Item.setPrice(int price) {
        this.price = price;
    }
    
    public String Item.getDescription() {
        return this.description;
    }
    
    public void Item.setDescription(String description) {
        this.description = description;
    }
    
    public String Item.getPictureUrl() {
        return this.pictureUrl;
    }
    
    public void Item.setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public Set<SaleDetail> Item.getSaleDetailList() {
        return this.saleDetailList;
    }
    
    public void Item.setSaleDetailList(Set<SaleDetail> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }
    
}
