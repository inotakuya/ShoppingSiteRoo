// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.repository;

import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.repository.ConsumerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect ConsumerRepository_Roo_Jpa_Repository {
    
    declare parents: ConsumerRepository extends JpaRepository<Consumer, Long>;
    
    declare parents: ConsumerRepository extends JpaSpecificationExecutor<Consumer>;
    
    declare @type: ConsumerRepository: @Repository;
    
}
