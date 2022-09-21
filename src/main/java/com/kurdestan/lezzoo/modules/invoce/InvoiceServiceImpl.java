package com.kurdestan.lezzoo.modules.invoce;

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
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "invoiceCache", allEntries = true),
    })
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "invoiceCache", allEntries = true),
    })
    public Invoice update(Invoice invoice) {
        Invoice lastSavedInvoice = getById(invoice.getId());
        lastSavedInvoice.setTotalPrice(invoice.getTotalPrice());
        lastSavedInvoice.setOrder(invoice.getOrder());
        lastSavedInvoice.setAppUser(invoice.getAppUser());
        return invoiceRepository.save(lastSavedInvoice);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "invoiceCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        invoiceRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "invoiceCache", key = "#id")
    public Invoice getById(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isEmpty()) {
            throw new NotFoundException("Invoice Not Found!");
        }
        return optionalInvoice.get();
    }

    @Override
    public List<Invoice> getAllByOrderId(Long orderId) {
        return invoiceRepository.findAllByOrder_Id(orderId);
    }

    @Override
    public List<Invoice> getAllByAppUserId(Long appUserId) {
        return invoiceRepository.findAllByOrder_Id(appUserId);
    }

    @Override
    public Page<Invoice> paging(Integer page, Integer size) {
        return invoiceRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Invoice> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Invoice> invoiceSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> invoiceSpecification.add(criteria));
        return invoiceRepository.findAll(invoiceSpecification);
    }

}


