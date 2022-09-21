package com.kurdestan.lezzoo.modules.invoce;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    Invoice toInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO toInvoiceDTO(Invoice invoice);
    List<Invoice> toInvoiceList(List<InvoiceDTO> invoiceDTOS);
    List<InvoiceDTO> toInvoiceDTOList(List<Invoice> invoices);
}
