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
import javax.persistence.TypedQuery;
import javax.persistence.Version;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findItemsByItemNameLike" })
public class Item {

    /**
     */
    @NotNull
    private long itemId;

    /**
     */
    @NotNull
    private String itemName;

    /**
     */
    @NotNull
    private int price;

    /**
     */
    @NotNull
    private String description;

    /**
     */
    @NotNull
    private String pictureUrl;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SaleDetails> saleDetailList = new HashSet<SaleDetails>();

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("itemId", "itemName", "price", "description", "pictureUrl", "saleDetailList");

	public static final EntityManager entityManager() {
        EntityManager em = new Item().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countItems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Item o", Long.class).getSingleResult();
    }

	public static List<Item> findAllItems() {
        return entityManager().createQuery("SELECT o FROM Item o", Item.class).getResultList();
    }

	public static List<Item> findAllItems(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Item o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Item.class).getResultList();
    }

	public static Item findItem(Long id) {
        if (id == null) return null;
        return entityManager().find(Item.class, id);
    }

	public static List<Item> findItemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Item o", Item.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Item> findItemEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Item o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Item.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Item attached = Item.findItem(this.id);
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
    public Item merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Item merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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

	public static Long countFindItemsByItemNameLike(String itemName) {
        if (itemName == null || itemName.length() == 0) throw new IllegalArgumentException("The itemName argument is required");
        itemName = itemName.replace('*', '%');
        if (itemName.charAt(0) != '%') {
            itemName = "%" + itemName;
        }
        if (itemName.charAt(itemName.length() - 1) != '%') {
            itemName = itemName + "%";
        }
        EntityManager em = Item.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Item AS o WHERE LOWER(o.itemName) LIKE LOWER(:itemName)", Long.class);
        q.setParameter("itemName", itemName);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Item> findItemsByItemNameLike(String itemName) {
        if (itemName == null || itemName.length() == 0) throw new IllegalArgumentException("The itemName argument is required");
        itemName = itemName.replace('*', '%');
        if (itemName.charAt(0) != '%') {
            itemName = "%" + itemName;
        }
        if (itemName.charAt(itemName.length() - 1) != '%') {
            itemName = itemName + "%";
        }
        EntityManager em = Item.entityManager();
        TypedQuery<Item> q = em.createQuery("SELECT o FROM Item AS o WHERE LOWER(o.itemName) LIKE LOWER(:itemName)", Item.class);
        q.setParameter("itemName", itemName);
        return q;
    }

	public static TypedQuery<Item> findItemsByItemNameLike(String itemName, String sortFieldName, String sortOrder) {
        if (itemName == null || itemName.length() == 0) throw new IllegalArgumentException("The itemName argument is required");
        itemName = itemName.replace('*', '%');
        if (itemName.charAt(0) != '%') {
            itemName = "%" + itemName;
        }
        if (itemName.charAt(itemName.length() - 1) != '%') {
            itemName = itemName + "%";
        }
        EntityManager em = Item.entityManager();
        String jpaQuery = "SELECT o FROM Item AS o WHERE LOWER(o.itemName) LIKE LOWER(:itemName)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Item> q = em.createQuery(jpaQuery, Item.class);
        q.setParameter("itemName", itemName);
        return q;
    }

	public long getItemId() {
        return this.itemId;
    }

	public void setItemId(long itemId) {
        this.itemId = itemId;
    }

	public String getItemName() {
        return this.itemName;
    }

	public void setItemName(String itemName) {
        this.itemName = itemName;
    }

	public int getPrice() {
        return this.price;
    }

	public void setPrice(int price) {
        this.price = price;
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public String getPictureUrl() {
        return this.pictureUrl;
    }

	public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

	public Set<SaleDetails> getSaleDetailList() {
        return this.saleDetailList;
    }

	public void setSaleDetailList(Set<SaleDetails> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }
}
