package jp.com.inotaku.repository;
import jp.com.inotaku.domain.Consumer;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Consumer.class)
public interface ConsumerRepository {
}
