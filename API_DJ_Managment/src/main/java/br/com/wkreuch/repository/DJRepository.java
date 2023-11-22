package br.com.wkreuch.repository;

import br.com.wkreuch.model.DJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DJRepository extends JpaRepository<DJ, Long> {
}
