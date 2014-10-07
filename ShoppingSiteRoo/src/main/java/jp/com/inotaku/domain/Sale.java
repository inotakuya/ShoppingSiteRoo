package jp.com.inotaku.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Sale {

    /**
     */
    @NotNull
    private long saleId;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateDate;

    /**
     */
    private int totalAmount;

    /**
     */
    @ManyToOne
    private Consumer consumer;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SaleDetails> saleDetailList = new HashSet<SaleDetails>();

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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("saleId", "updateDate", "totalAmount", "consumer", "saleDetailList");

	public static final EntityManager entityManager() {
        EntityManager em = new Sale().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSales() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Sale o", Long.class).getSingleResult();
    }

	public static List<Sale> findAllSales() {
        return entityManager().createQuery("SELECT o FROM Sale o", Sale.class).getResultList();
    }

	public static List<Sale> findAllSales(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Sale o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Sale.class).getResultList();
    }

	public static Sale findSale(Long id) {
        if (id == null) return null;
        return entityManager().find(Sale.class, id);
    }

	public static List<Sale> findSaleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Sale o", Sale.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Sale> findSaleEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Sale o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Sale.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Sale attached = Sale.findSale(this.id);
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
    public Sale merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Sale merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public long getSaleId() {
        return this.saleId;
    }

	public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

	public Date getUpdateDate() {
        return this.updateDate;
    }

	public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public int getTotalAmount() {
        return this.totalAmount;
    }

	public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

	public Consumer getConsumer() {
        return this.consumer;
    }

	public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

	public Set<SaleDetails> getSaleDetailList() {
        return this.saleDetailList;
    }

	public void setSaleDetailList(Set<SaleDetails> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
