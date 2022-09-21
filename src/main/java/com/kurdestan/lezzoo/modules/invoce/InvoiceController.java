package com.kurdestan.lezzoo.modules.invoce;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/invoices/")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService service;
    private InvoiceMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice invoice = mapper.toInvoice(invoiceDTO);
        service.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice invoice = mapper.toInvoice(invoiceDTO);
        service.update(invoice);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<InvoiceDTO> getById(@PathVariable Long id) {
        Invoice invoice = service.getById(id);
        InvoiceDTO invoiceDTO = mapper.toInvoiceDTO(invoice);
        return ResponseEntity.ok(invoiceDTO);
    }

    @GetMapping("/v1/order/{orderId}")
    public ResponseEntity<List<InvoiceDTO>> getAllByOrderId(@PathVariable Long orderId) {
        List<Invoice> invoices = service.getAllByOrderId(orderId);
        List<InvoiceDTO> invoiceDTOS = mapper.toInvoiceDTOList(invoices);
        return ResponseEntity.ok(invoiceDTOS);
    }

    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<List<InvoiceDTO>> getAllByUserId(@PathVariable Long userId) {
        List<Invoice> invoices = service.getAllByAppUserId(userId);
        List<InvoiceDTO> invoiceDTOS = mapper.toInvoiceDTOList(invoices);
        return ResponseEntity.ok(invoiceDTOS);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<InvoiceDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Invoice> invoicePage = service.paging(page, size);
        return getPagingDataResponseEntity(page, invoicePage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<InvoiceDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Invoice> invoiceList = service.search(searchCriteria);
        List<InvoiceDTO> invoiceDTOS = mapper.toInvoiceDTOList(invoiceList);
        return ResponseEntity.ok(invoiceDTOS);
    }

    private ResponseEntity<PagingData<InvoiceDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Invoice> invoicePage) {
        int totalPage = invoicePage.getTotalPages();
        List<Invoice> data = invoicePage.getContent();
        List<InvoiceDTO> invoiceDTOS = mapper.toInvoiceDTOList(data);
        PagingData<InvoiceDTO> pagingData = new PagingData<>(totalPage, page, invoiceDTOS);
        return ResponseEntity.ok(pagingData);
    }

}




