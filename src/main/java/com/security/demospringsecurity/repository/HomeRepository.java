package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Home;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home,Long> {
}