// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import jp.com.inotaku.domain.Consumer;

privileged aspect Consumer_Roo_Jpa_Entity {
    
    declare @type: Consumer: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Consumer.id;
    
    @Version
    @Column(name = "version")
    private Integer Consumer.version;
    
    public Long Consumer.getId() {
        return this.id;
    }
    
    public void Consumer.setId(Long id) {
        this.id = id;
    }
    
    public Integer Consumer.getVersion() {
        return this.version;
    }
    
    public void Consumer.setVersion(Integer version) {
        this.version = version;
    }
    
}
