package jp.com.inotaku.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;

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
}
