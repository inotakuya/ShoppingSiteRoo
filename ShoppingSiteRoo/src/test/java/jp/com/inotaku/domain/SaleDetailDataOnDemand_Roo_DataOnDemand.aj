// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import jp.com.inotaku.domain.Item;
import jp.com.inotaku.domain.ItemDataOnDemand;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.domain.SaleDataOnDemand;
import jp.com.inotaku.domain.SaleDetail;
import jp.com.inotaku.domain.SaleDetailDataOnDemand;
import jp.com.inotaku.repository.SaleDetailRepository;
import jp.com.inotaku.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect SaleDetailDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleDetailDataOnDemand: @Component;
    
    private Random SaleDetailDataOnDemand.rnd = new SecureRandom();
    
    private List<SaleDetail> SaleDetailDataOnDemand.data;
    
    @Autowired
    ItemDataOnDemand SaleDetailDataOnDemand.itemDataOnDemand;
    
    @Autowired
    SaleDataOnDemand SaleDetailDataOnDemand.saleDataOnDemand;
    
    @Autowired
    SaleDetailService SaleDetailDataOnDemand.saleDetailService;
    
    @Autowired
    SaleDetailRepository SaleDetailDataOnDemand.saleDetailRepository;
    
    public SaleDetail SaleDetailDataOnDemand.getNewTransientSaleDetail(int index) {
        SaleDetail obj = new SaleDetail();
        setItem(obj, index);
        setQuantity(obj, index);
        setSale(obj, index);
        setSaleDetailId(obj, index);
        setUpdateDate(obj, index);
        return obj;
    }
    
    public void SaleDetailDataOnDemand.setItem(SaleDetail obj, int index) {
        Item item = itemDataOnDemand.getRandomItem();
        obj.setItem(item);
    }
    
    public void SaleDetailDataOnDemand.setQuantity(SaleDetail obj, int index) {
        int quantity = index;
        if (quantity < 0) {
            quantity = 0;
        }
        obj.setQuantity(quantity);
    }
    
    public void SaleDetailDataOnDemand.setSale(SaleDetail obj, int index) {
        Sale sale = saleDataOnDemand.getRandomSale();
        obj.setSale(sale);
    }
    
    public void SaleDetailDataOnDemand.setSaleDetailId(SaleDetail obj, int index) {
        Long saleDetailId = new Integer(index).longValue();
        obj.setSaleDetailId(saleDetailId);
    }
    
    public void SaleDetailDataOnDemand.setUpdateDate(SaleDetail obj, int index) {
        Date updateDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateDate(updateDate);
    }
    
    public SaleDetail SaleDetailDataOnDemand.getSpecificSaleDetail(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SaleDetail obj = data.get(index);
        Long id = obj.getId();
        return saleDetailService.findSaleDetail(id);
    }
    
    public SaleDetail SaleDetailDataOnDemand.getRandomSaleDetail() {
        init();
        SaleDetail obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return saleDetailService.findSaleDetail(id);
    }
    
    public boolean SaleDetailDataOnDemand.modifySaleDetail(SaleDetail obj) {
        return false;
    }
    
    public void SaleDetailDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = saleDetailService.findSaleDetailEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SaleDetail' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<SaleDetail>();
        for (int i = 0; i < 10; i++) {
            SaleDetail obj = getNewTransientSaleDetail(i);
            try {
                saleDetailService.saveSaleDetail(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            saleDetailRepository.flush();
            data.add(obj);
        }
    }
    
}
