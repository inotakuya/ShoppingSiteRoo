package jp.com.inotaku.repository;
import jp.com.inotaku.domain.Item;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Item.class)
public interface ItemRepository {
}
