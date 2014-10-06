package jp.com.inotaku.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

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
}
