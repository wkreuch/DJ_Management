package br.com.wkreuch.utils.mapper;

import org.springframework.beans.BeanUtils;

public class BeanUtilsMapper {

    public static <O, D> void copyProperties(O origin, D destination,String... ignoreProperties) {
        if (origin == null || destination == null) return;

        BeanUtils.copyProperties(origin, destination, ignoreProperties);
    }

}
