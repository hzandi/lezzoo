package com.kurdestan.lezzoo.modules.invoce;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface InvoiceService {
    Invoice save(Invoice invoice);
    Invoice update(Invoice invoice);
    void delete(Long id);
    Invoice getById(Long id);
    List<Invoice> getAllByOrderId(Long orderId);
    List<Invoice> getAllByAppUserId(Long appUserId);
    Page<Invoice> paging(Integer page, Integer size);
    List<Invoice> search(List<SearchCriteria> searchCriteria);
}
