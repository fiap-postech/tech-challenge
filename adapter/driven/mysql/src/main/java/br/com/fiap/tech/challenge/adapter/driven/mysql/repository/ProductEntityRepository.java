package br.com.fiap.tech.challenge.adapter.driven.mysql.repository;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByUuid(String uuid);

    Page<ProductEntity> findAllByEnabledTrue(Pageable pageable);

    Page<ProductEntity> findAllByEnabledTrueAndCategory(ProductCategory category, Pageable pageable);
}
