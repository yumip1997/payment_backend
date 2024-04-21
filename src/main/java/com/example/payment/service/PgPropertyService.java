package com.example.payment.service;

import com.example.payment.dto.request.PgPropertyRequest;
import com.example.payment.dto.response.TossProperty;
import com.example.payment.manager.PgPropertyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PgPropertyService {

    private final PgPropertyManager pgPropertyManager;

    public TossProperty getTossProperty(PgPropertyRequest pgPropertyRequest){
        return pgPropertyManager.lookupTossProperty(pgPropertyRequest.getPgId());
    }

}
