package jp.com.inotaku.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Consumer {

    /**
     */
    @NotNull
    private String consumerName;

    /**
     */
    @NotNull
    private String password;

    /**
     */
    @NotNull
    private String address;

    /**
     */
    @NotNull
    private String email;

    /**
     */
    private int point;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Sale> sale = new HashSet<Sale>();

	public String getConsumerName() {
        return this.consumerName;
    }

	public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getAddress() {
        return this.address;
    }

	public void setAddress(String address) {
        this.address = address;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public int getPoint() {
        return this.point;
    }

	public void setPoint(int point) {
        this.point = point;
    }

	public Set<Sale> getSale() {
        return this.sale;
    }

	public void setSale(Set<Sale> sale) {
        this.sale = sale;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("consumerName", "password", "address", "email", "point", "sale");

	public static final EntityManager entityManager() {
        EntityManager em = new Consumer().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countConsumers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Consumer o", Long.class).getSingleResult();
    }

	public static List<Consumer> findAllConsumers() {
        return entityManager().createQuery("SELECT o FROM Consumer o", Consumer.class).getResultList();
    }

	public static List<Consumer> findAllConsumers(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Consumer o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Consumer.class).getResultList();
    }

	public static Consumer findConsumer(Long id) {
        if (id == null) return null;
        return entityManager().find(Consumer.class, id);
    }

	public static List<Consumer> findConsumerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Consumer o", Consumer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Consumer> findConsumerEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Consumer o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Consumer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Consumer attached = Consumer.findConsumer(this.id);
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
    public Consumer merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Consumer merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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
