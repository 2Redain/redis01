package com.spring.temp.dome.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisObjectSerializer implements RedisSerializer<Object> {
	
	/*
	 * BYTE就是unsigned char... 只是比你直接敲unsigned char少9个字母
	 * 
	 * serializer 序列化，将对象拆分成byte
	 * deserializer 反序列化，将一堆东西重新组成对象
	 * converter 转化器
	 */
	private Converter<Object,byte[]> serializer = new SerializingConverter();
	private Converter<byte[],Object> deserializer = new DeserializingConverter();
	
	static final byte[] EMPTY_ARRAY = new byte[0];

    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(object);
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }
    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
	
}
