package com.kurdestan.lezzoo.modules.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppUserRepository extends
        PagingAndSortingRepository<AppUser, Long>,
        JpaSpecificationExecutor<AppUser> {

    AppUser findByUsername(String username);
    Page<AppUser> findAll(Pageable pageable);
    List<AppUser> findAll(Specification<AppUser> specification);
}
