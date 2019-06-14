package com.exampleDemoPdfH.demoDemoPdfH.repository;

import com.exampleDemoPdfH.demoDemoPdfH.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {
}
