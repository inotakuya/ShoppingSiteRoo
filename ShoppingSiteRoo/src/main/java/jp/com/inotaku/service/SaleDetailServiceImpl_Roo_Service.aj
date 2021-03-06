// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.service;

import java.util.List;
import jp.com.inotaku.domain.SaleDetail;
import jp.com.inotaku.repository.SaleDetailRepository;
import jp.com.inotaku.service.SaleDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleDetailServiceImpl_Roo_Service {
    
    declare @type: SaleDetailServiceImpl: @Service;
    
    declare @type: SaleDetailServiceImpl: @Transactional;
    
    @Autowired
    SaleDetailRepository SaleDetailServiceImpl.saleDetailRepository;
    
    public long SaleDetailServiceImpl.countAllSaleDetails() {
        return saleDetailRepository.count();
    }
    
    public void SaleDetailServiceImpl.deleteSaleDetail(SaleDetail saleDetail) {
        saleDetailRepository.delete(saleDetail);
    }
    
    public SaleDetail SaleDetailServiceImpl.findSaleDetail(Long id) {
        return saleDetailRepository.findOne(id);
    }
    
    public List<SaleDetail> SaleDetailServiceImpl.findAllSaleDetails() {
        return saleDetailRepository.findAll();
    }
    
    public List<SaleDetail> SaleDetailServiceImpl.findSaleDetailEntries(int firstResult, int maxResults) {
        return saleDetailRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void SaleDetailServiceImpl.saveSaleDetail(SaleDetail saleDetail) {
        saleDetailRepository.save(saleDetail);
    }
    
    public SaleDetail SaleDetailServiceImpl.updateSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }
    
}
