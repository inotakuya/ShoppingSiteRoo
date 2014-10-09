package jp.com.inotaku.domain;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Consumer.class)
public class ConsumerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Consumer> data;

	public Consumer getNewTransientConsumer(int index) {
        Consumer obj = new Consumer();
        setAddress(obj, index);
        setConsumerName(obj, index);
        setEmail(obj, index);
        setPassword(obj, index);
        setPoint(obj, index);
        return obj;
    }

	public void setAddress(Consumer obj, int index) {
        String address = "address_" + index;
        obj.setAddress(address);
    }

	public void setConsumerName(Consumer obj, int index) {
        String consumerName = "consumerName_" + index;
        obj.setConsumerName(consumerName);
    }

	public void setEmail(Consumer obj, int index) {
        String email = "foo" + index + "@bar.com";
        obj.setEmail(email);
    }

	public void setPassword(Consumer obj, int index) {
        String password = "password_" + index;
        obj.setPassword(password);
    }

	public void setPoint(Consumer obj, int index) {
        int point = index;
        obj.setPoint(point);
    }

	public Consumer getSpecificConsumer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Consumer obj = data.get(index);
        Long id = obj.getId();
        return Consumer.findConsumer(id);
    }

	public Consumer getRandomConsumer() {
        init();
        Consumer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Consumer.findConsumer(id);
    }

	public boolean modifyConsumer(Consumer obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Consumer.findConsumerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Consumer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Consumer>();
        for (int i = 0; i < 10; i++) {
            Consumer obj = getNewTransientConsumer(i);
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