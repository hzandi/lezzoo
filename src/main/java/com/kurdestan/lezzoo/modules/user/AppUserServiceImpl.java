package com.kurdestan.lezzoo.modules.user;

import com.kurdestan.lezzoo.common.SearchCriteria;
import com.kurdestan.lezzoo.common.SearchSpecification;
import com.kurdestan.lezzoo.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "appUserCache", allEntries = true),
    })
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "appUserCache", allEntries = true),
    })
    public AppUser update(AppUser appUser) {
        AppUser lastSavedAppUser = getById(appUser.getId());
        lastSavedAppUser.setName(appUser.getName());
        lastSavedAppUser.setEmail(appUser.getEmail());
        lastSavedAppUser.setUsername(appUser.getUsername());
        lastSavedAppUser.setPhone(appUser.getPhone());
        lastSavedAppUser.setPassword(appUser.getPassword());
        return appUserRepository.save(lastSavedAppUser);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "appUserCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        appUserRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "appUserCache", key = "#id")
    public AppUser getById(Long id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
        if (optionalAppUser.isEmpty()) {
            throw new NotFoundException("User Not Found!");
        }
        return optionalAppUser.get();
    }

    @Override
    public Page<AppUser> paging(Integer page, Integer size) {
        return appUserRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<AppUser> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<AppUser> appUserSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> appUserSpecification.add(criteria));
        return appUserRepository.findAll(appUserSpecification);
    }

}

