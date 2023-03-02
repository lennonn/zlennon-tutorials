package com.zlennon.chatgpt.util;

import cn.hutool.core.util.RandomUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class IDGenerator implements IdentifierGenerator {

    int i=10;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return RandomUtil.randomInt();
    }
}
