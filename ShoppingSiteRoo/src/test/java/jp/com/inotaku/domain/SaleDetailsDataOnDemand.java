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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = SaleDetails.class)
public class SaleDetailsDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<SaleDetails> data;

	@Autowired
    ItemDataOnDemand itemDataOnDemand;

	@Autowired
    SaleDataOnDemand saleDataOnDemand;

	public SaleDetails getNewTransientSaleDetails(int index) {
        SaleDetails obj = new SaleDetails();
        setQuantity(obj, index);
        setSaleDetailsId(obj, index);
        setUpdateDate(obj, index);
        return obj;
    }

	public void setQuantity(SaleDetails obj, int index) {
        int quantity = index;
        obj.setQuantity(quantity);
    }

	public void setSaleDetailsId(SaleDetails obj, int index) {
        Long saleDetailsId = new Integer(index).longValue();
        obj.setSaleDetailsId(saleDetailsId);
    }

	public void setUpdateDate(SaleDetails obj, int index) {
        Date updateDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateDate(updateDate);
    }

	public SaleDetails getSpecificSaleDetails(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SaleDetails obj = data.get(index);
        Long id = obj.getId();
        return SaleDetails.findSaleDetails(id);
    }

	public SaleDetails getRandomSaleDetails() {
        init();
        SaleDetails obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return SaleDetails.findSaleDetails(id);
    }

	public boolean modifySaleDetails(SaleDetails obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = SaleDetails.findSaleDetailsEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SaleDetails' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<SaleDetails>();
        for (int i = 0; i < 10; i++) {
            SaleDetails obj = getNewTransientSaleDetails(i);
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
