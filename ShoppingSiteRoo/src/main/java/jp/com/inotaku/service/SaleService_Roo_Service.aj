// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.service;

import java.util.List;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.service.SaleService;

privileged aspect SaleService_Roo_Service {
    
    public abstract long SaleService.countAllSales();    
    public abstract void SaleService.deleteSale(Sale sale);    
    public abstract Sale SaleService.findSale(Long id);    
    public abstract List<Sale> SaleService.findAllSales();    
    public abstract List<Sale> SaleService.findSaleEntries(int firstResult, int maxResults);    
    public abstract void SaleService.saveSale(Sale sale);    
    public abstract Sale SaleService.updateSale(Sale sale);    
}
