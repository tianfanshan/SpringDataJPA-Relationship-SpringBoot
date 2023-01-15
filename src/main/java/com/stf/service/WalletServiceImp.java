package com.stf.service;

import com.stf.entity.Wallet;
import com.stf.entity.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImp implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    @Override
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).get();
    }
}
