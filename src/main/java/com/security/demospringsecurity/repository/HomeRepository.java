package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Home;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home,Long> {
    Iterable<Home> findAllByName(String name);

}
