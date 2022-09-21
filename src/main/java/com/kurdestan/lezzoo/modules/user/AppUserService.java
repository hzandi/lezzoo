package com.kurdestan.lezzoo.modules.user;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AppUserService {
    AppUser save(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(Long id);
    AppUser getById(Long id);
    Page<AppUser> paging(Integer page, Integer size);
    List<AppUser> search(List<SearchCriteria> searchCriteria);
}
