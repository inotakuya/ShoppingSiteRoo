package jp.com.inotaku.domain;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@RooIntegrationTest(entity = Consumer.class)
public class ConsumerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ConsumerDataOnDemand dod;

	@Test
    public void testCountConsumers() {
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", dod.getRandomConsumer());
        long count = Consumer.countConsumers();
        Assert.assertTrue("Counter for 'Consumer' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindConsumer() {
        Consumer obj = dod.getRandomConsumer();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to provide an identifier", id);
        obj = Consumer.findConsumer(id);
        Assert.assertNotNull("Find method for 'Consumer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Consumer' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllConsumers() {
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", dod.getRandomConsumer());
        long count = Consumer.countConsumers();
        Assert.assertTrue("Too expensive to perform a find all test for 'Consumer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Consumer> result = Consumer.findAllConsumers();
        Assert.assertNotNull("Find all method for 'Consumer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Consumer' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindConsumerEntries() {
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", dod.getRandomConsumer());
        long count = Consumer.countConsumers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Consumer> result = Consumer.findConsumerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Consumer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Consumer' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Consumer obj = dod.getRandomConsumer();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to provide an identifier", id);
        obj = Consumer.findConsumer(id);
        Assert.assertNotNull("Find method for 'Consumer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyConsumer(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Consumer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Consumer obj = dod.getRandomConsumer();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to provide an identifier", id);
        obj = Consumer.findConsumer(id);
        boolean modified =  dod.modifyConsumer(obj);
        Integer currentVersion = obj.getVersion();
        Consumer merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Consumer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", dod.getRandomConsumer());
        Consumer obj = dod.getNewTransientConsumer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Consumer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Consumer' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Consumer' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Consumer obj = dod.getRandomConsumer();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Consumer' failed to provide an identifier", id);
        obj = Consumer.findConsumer(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Consumer' with identifier '" + id + "'", Consumer.findConsumer(id));
    }
}
