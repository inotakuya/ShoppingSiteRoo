package jp.com.inotaku.repository;
import jp.com.inotaku.domain.Sale;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Sale.class)
public interface SaleRepository {
}
