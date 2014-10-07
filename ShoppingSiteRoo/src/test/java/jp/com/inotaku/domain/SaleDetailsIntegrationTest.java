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
@RooIntegrationTest(entity = SaleDetails.class)
public class SaleDetailsIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SaleDetailsDataOnDemand dod;

	@Test
    public void testCountSaleDetailses() {
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", dod.getRandomSaleDetails());
        long count = SaleDetails.countSaleDetailses();
        Assert.assertTrue("Counter for 'SaleDetails' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSaleDetails() {
        SaleDetails obj = dod.getRandomSaleDetails();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to provide an identifier", id);
        obj = SaleDetails.findSaleDetails(id);
        Assert.assertNotNull("Find method for 'SaleDetails' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SaleDetails' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSaleDetailses() {
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", dod.getRandomSaleDetails());
        long count = SaleDetails.countSaleDetailses();
        Assert.assertTrue("Too expensive to perform a find all test for 'SaleDetails', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SaleDetails> result = SaleDetails.findAllSaleDetailses();
        Assert.assertNotNull("Find all method for 'SaleDetails' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SaleDetails' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSaleDetailsEntries() {
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", dod.getRandomSaleDetails());
        long count = SaleDetails.countSaleDetailses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SaleDetails> result = SaleDetails.findSaleDetailsEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SaleDetails' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SaleDetails' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        SaleDetails obj = dod.getRandomSaleDetails();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to provide an identifier", id);
        obj = SaleDetails.findSaleDetails(id);
        Assert.assertNotNull("Find method for 'SaleDetails' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleDetails(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'SaleDetails' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        SaleDetails obj = dod.getRandomSaleDetails();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to provide an identifier", id);
        obj = SaleDetails.findSaleDetails(id);
        boolean modified =  dod.modifySaleDetails(obj);
        Integer currentVersion = obj.getVersion();
        SaleDetails merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SaleDetails' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", dod.getRandomSaleDetails());
        SaleDetails obj = dod.getNewTransientSaleDetails(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SaleDetails' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'SaleDetails' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        SaleDetails obj = dod.getRandomSaleDetails();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SaleDetails' failed to provide an identifier", id);
        obj = SaleDetails.findSaleDetails(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'SaleDetails' with identifier '" + id + "'", SaleDetails.findSaleDetails(id));
    }
}
