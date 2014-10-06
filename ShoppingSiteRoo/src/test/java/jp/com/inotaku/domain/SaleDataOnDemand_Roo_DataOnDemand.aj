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
import jp.com.inotaku.domain.ConsumerDataOnDemand;
import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.domain.SaleDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect SaleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: SaleDataOnDemand: @Component;
    
    private Random SaleDataOnDemand.rnd = new SecureRandom();
    
    private List<Sale> SaleDataOnDemand.data;
    
    @Autowired
    ConsumerDataOnDemand SaleDataOnDemand.consumerDataOnDemand;
    
    public Sale SaleDataOnDemand.getNewTransientSale(int index) {
        Sale obj = new Sale();
        setSaleId(obj, index);
        setTotalAmount(obj, index);
        setUpdateDate(obj, index);
        return obj;
    }
    
    public void SaleDataOnDemand.setSaleId(Sale obj, int index) {
        Long saleId = new Integer(index).longValue();
        obj.setSaleId(saleId);
    }
    
    public void SaleDataOnDemand.setTotalAmount(Sale obj, int index) {
        int totalAmount = index;
        obj.setTotalAmount(totalAmount);
    }
    
    public void SaleDataOnDemand.setUpdateDate(Sale obj, int index) {
        Date updateDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateDate(updateDate);
    }
    
    public Sale SaleDataOnDemand.getSpecificSale(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Sale obj = data.get(index);
        Long id = obj.getId();
        return Sale.findSale(id);
    }
    
    public Sale SaleDataOnDemand.getRandomSale() {
        init();
        Sale obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Sale.findSale(id);
    }
    
    public boolean SaleDataOnDemand.modifySale(Sale obj) {
        return false;
    }
    
    public void SaleDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Sale.findSaleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Sale' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Sale>();
        for (int i = 0; i < 10; i++) {
            Sale obj = getNewTransientSale(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
