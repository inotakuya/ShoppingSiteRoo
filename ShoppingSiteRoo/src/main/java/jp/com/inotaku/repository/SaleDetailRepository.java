package jp.com.inotaku.repository;
import jp.com.inotaku.domain.SaleDetail;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = SaleDetail.class)
public interface SaleDetailRepository {
}
