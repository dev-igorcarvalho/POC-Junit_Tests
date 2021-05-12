package br.com.igorcarvalho.tests.examples.api.repository;

import br.com.igorcarvalho.tests.examples.api.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by : 01001001 01000011 at 12/05/2021
 */
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
