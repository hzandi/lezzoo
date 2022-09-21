package com.kurdestan.lezzoo.modules.user;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/users/")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService service;
    private AppUserMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = mapper.toAppUser(appUserDTO);
        service.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = mapper.toAppUser(appUserDTO);
        service.update(appUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<AppUserDTO> getById(@PathVariable Long id) {
        AppUser appUser = service.getById(id);
        AppUserDTO appUserDTO = mapper.toAppUserDTO(appUser);
        return ResponseEntity.ok(appUserDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<AppUserDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<AppUser> appUserPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, appUserPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<AppUserDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<AppUser> appUserList = service.search(searchCriteria);
        List<AppUserDTO> appUserDTOS = mapper.toAppUserDTOList(appUserList);
        return ResponseEntity.ok(appUserDTOS);
    }

    private ResponseEntity<PagingData<AppUserDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<AppUser> appUserPage) {
        int totalPage = appUserPage.getTotalPages();
        List<AppUser> data = appUserPage.getContent();
        List<AppUserDTO> appUserDTOS = mapper.toAppUserDTOList(data);
        PagingData<AppUserDTO> pagingData = new PagingData<>(totalPage, page, appUserDTOS);
        return ResponseEntity.ok(pagingData);
    }

}




