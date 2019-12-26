package com.repository;

import com.model.TypeHome;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHomeRepository extends PagingAndSortingRepository<TypeHome,Long> {
}
