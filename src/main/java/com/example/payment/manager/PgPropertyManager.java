package com.example.payment.manager;

import com.example.payment.dto.response.TossProperty;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "pg")
@RequiredArgsConstructor
public class PgPropertyManager {

    private final List<TossProperty> tossPropertyList;

    private Map<String, TossProperty> tossPropertyMap;

    @PostConstruct
    void initMap(){
        tossPropertyMap = convertMapByMid(tossPropertyList, TossProperty::getMid);
    }

    private <T> Map<String, T> convertMapByMid(List<T> list, Function<T, String> midMapper){
        if(CollectionUtils.isEmpty(list)){
            return Collections.emptyMap();
        }

        return list.stream()
                .collect(Collectors.toMap(midMapper, Function.identity(), (o1,o2) -> o1));
    }

    public TossProperty lookupTossProperty(String mid){
        return tossPropertyMap.get(mid);
    }
}
