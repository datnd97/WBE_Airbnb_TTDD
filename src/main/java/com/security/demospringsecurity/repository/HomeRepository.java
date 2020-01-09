package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface HomeRepository extends PagingAndSortingRepository<Home,Long> {
    Iterable<Home> findAllByName(String name);
//    @Query(value = "from Booking t where yourDate BETWEEN :startDate AND :endDate")
//    public List<EntityClassTable> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

//    Iterable<Home> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);
    Optional<Home> findByBedroom(Integer bedroom);
    Optional<Home> findByBathroom(Integer bathroom);
    Optional<Home> findByTypeRoom(TypeRoom typeRoom);
    Optional<Home> findByTypeHome(TypeHome typeHome);
    Optional<Home> findByAddress(String address);
    Optional<Home> findByPrice(Double price);
    Optional<Home> findByDescription(String description);



}
