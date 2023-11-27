package com.javastart.bill.controller;

import com.javastart.bill.dto.BillRq;
import com.javastart.bill.dto.BillRs;
import com.javastart.bill.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping("/{billId}")
    public BillRs getBill(@PathVariable Long billId) {
        return new BillRs(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRq billRq) {
        return billService.createBill(billRq.getAccountId(), billRq.getAmount(),
                billRq.getIsDefault(), billRq.getOverDraftEnabled());
    }

    @PutMapping("/{buildId}")
    public BillRs updateBill(@PathVariable Long buildId, @RequestBody BillRq billRq) {
        return new BillRs(billService.updateBill(buildId, billRq.getAccountId(),
                billRq.getAmount(), billRq.getIsDefault(),
                billRq.getOverDraftEnabled()));
    }

    @DeleteMapping("/{buildId}")
    public BillRs deleteBill(@PathVariable Long buildId) {
        return new BillRs(billService.deleteBill(buildId));
    }


}
