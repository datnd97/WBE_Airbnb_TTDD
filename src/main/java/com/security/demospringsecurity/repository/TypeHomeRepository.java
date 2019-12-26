package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHomeRepository extends PagingAndSortingRepository<TypeHome,Long> {
}
