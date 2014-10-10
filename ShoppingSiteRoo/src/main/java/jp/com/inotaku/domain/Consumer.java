package jp.com.inotaku.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class Consumer {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String consumerName;

    /**
     */
    @NotNull
    private String password;

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
    private Set<Sale> saleLiset = new HashSet<Sale>();
}
