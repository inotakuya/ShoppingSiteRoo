package jp.com.inotaku.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class SaleDetails {

    /**
     */
    @NotNull
    private long saleDetailsId;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateDate;

    /**
     */
    @NotNull
    private int quantity;

    /**
     */
    @ManyToOne
    private Sale sale;

    /**
     */
    @ManyToOne
    private Item item;

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("saleDetailsId", "updateDate", "quantity", "sale", "item");

	public static final EntityManager entityManager() {
        EntityManager em = new SaleDetails().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSaleDetailses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SaleDetails o", Long.class).getSingleResult();
    }

	public static List<SaleDetails> findAllSaleDetailses() {
        return entityManager().createQuery("SELECT o FROM SaleDetails o", SaleDetails.class).getResultList();
    }

	public static List<SaleDetails> findAllSaleDetailses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SaleDetails o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SaleDetails.class).getResultList();
    }

	public static SaleDetails findSaleDetails(Long id) {
        if (id == null) return null;
        return entityManager().find(SaleDetails.class, id);
    }

	public static List<SaleDetails> findSaleDetailsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SaleDetails o", SaleDetails.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<SaleDetails> findSaleDetailsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SaleDetails o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SaleDetails.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SaleDetails attached = SaleDetails.findSaleDetails(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public SaleDetails merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SaleDetails merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public long getSaleDetailsId() {
        return this.saleDetailsId;
    }

	public void setSaleDetailsId(long saleDetailsId) {
        this.saleDetailsId = saleDetailsId;
    }

	public Date getUpdateDate() {
        return this.updateDate;
    }

	public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public int getQuantity() {
        return this.quantity;
    }

	public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public Sale getSale() {
        return this.sale;
    }

	public void setSale(Sale sale) {
        this.sale = sale;
    }

	public Item getItem() {
        return this.item;
    }

	public void setItem(Item item) {
        this.item = item;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
}
