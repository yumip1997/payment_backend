package com.example.payment.hepler;

import com.example.payment.dto.request.TossApproveRequest;
import com.example.payment.dto.response.TossResponse;
import org.springframework.stereotype.Component;

@Component
public class TossApiCallHelper {

    public TossResponse callTossApprove(TossApproveRequest tossApproveRequest){

        return new TossResponse();
    }
}
