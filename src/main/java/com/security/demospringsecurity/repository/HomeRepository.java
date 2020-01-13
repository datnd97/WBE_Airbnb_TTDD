package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home,Long> {
    Iterable<Home> findAllByName(String name);
//    @Query(value = "from Booking t where yourDate BETWEEN :startDate AND :endDate")
//    public List<EntityClassTable> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

//    Iterable<Home> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);
    Iterable<Home> findByBedroom(Integer bedroom);
    Iterable<Home> findByBathroom(Integer bathroom);
    Iterable<Home> findByTypeRoom(TypeRoom typeRoom);
    Iterable<Home> findByTypeHome(TypeHome typeHome);
    Iterable<Home> findByAddress(String address);
    Iterable<Home> findByPrice(Double price);
    Iterable<Home> findByDescription(String description);



}
