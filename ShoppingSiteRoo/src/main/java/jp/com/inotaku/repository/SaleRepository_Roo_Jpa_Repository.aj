// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.repository;

import jp.com.inotaku.domain.Sale;
import jp.com.inotaku.repository.SaleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect SaleRepository_Roo_Jpa_Repository {
    
    declare parents: SaleRepository extends JpaRepository<Sale, Long>;
    
    declare parents: SaleRepository extends JpaSpecificationExecutor<Sale>;
    
    declare @type: SaleRepository: @Repository;
    
}