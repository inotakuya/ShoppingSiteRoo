package jp.com.inotaku.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
public class Item {

    /**
     */
    @NotNull
    @Column(unique = true)
    private long itemId;

    /**
     */
    @NotNull
    private String itemName;

    /**
     */
    @NotNull
    @Min(0L)
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
    private Set<SaleDetail> saleDetailList = new HashSet<SaleDetail>();
}
