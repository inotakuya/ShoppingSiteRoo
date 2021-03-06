// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.domain;

import java.util.Date;
import java.util.Set;
import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.domain.SaleDetail;

privileged aspect Sale_Roo_JavaBean {
    
    public long Sale.getSaleId() {
        return this.saleId;
    }
    
    public void Sale.setSaleId(long saleId) {
        this.saleId = saleId;
    }
    
    public Date Sale.getUpdateDate() {
        return this.updateDate;
    }
    
    public void Sale.setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public int Sale.getTotalAmount() {
        return this.totalAmount;
    }
    
    public void Sale.setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Consumer Sale.getConsumer() {
        return this.consumer;
    }
    
    public void Sale.setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
    
    public Set<SaleDetail> Sale.getSaleDetailList() {
        return this.saleDetailList;
    }
    
    public void Sale.setSaleDetailList(Set<SaleDetail> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }
    
}
