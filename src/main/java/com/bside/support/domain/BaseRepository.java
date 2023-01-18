package com.bside.support.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.NoSuchElementException;

@NoRepositoryBean
public interface BaseRepository<Aggregate extends BaseAggregateRoot<ID>, ID extends Serializable>
        extends JpaRepository<Aggregate, ID> {

    default @NotNull Aggregate getById(@NotNull ID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(id.toString()));
    }
}