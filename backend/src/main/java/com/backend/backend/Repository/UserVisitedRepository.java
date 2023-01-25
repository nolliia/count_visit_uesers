package com.backend.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.backend.Model.UserVisited;

public interface UserVisitedRepository extends JpaRepository<UserVisited, Long>{
    @Query( 
        value =  "select source,count(distinct	email,phone ) as count from user_visited uv group by source",
        nativeQuery = true)
    public List<Object[]> getResultsCount();
}
