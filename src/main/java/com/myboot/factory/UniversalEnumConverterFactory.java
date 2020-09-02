package com.myboot.factory;

import com.myboot.vo.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author Nansen
 * @create 2020/9/2 16:19
 */
public class UniversalEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> aClass) {
        Converter<Integer,T> converter = new Converter<Integer, T>() {
            @Override
            public T convert(Integer source) {
                T[] enums =  aClass.getEnumConstants();
                for(T t : enums){
                    if(source == t.getValue()){
                        return t;
                    }
                }
                return null;
            }
        };
        return converter;
    }
}
