package com.stf.controller;

import com.stf.entity.Wallet;
import com.stf.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets/{id}")
    public ResponseEntity<?> getWalletById(@PathVariable long id){
        Wallet wallet = walletService.getWalletById(id);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
}
