package com.example.fhdemo.cert.test;

import com.example.fhdemo.YamlFooProperties;
import com.example.fhdemo.cert.SM2Utils;
import com.example.fhdemo.util.SM2Util;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SM2EncryptDecryptTest {


    @Autowired
    private YamlFooProperties yamlFooProperties;

    @Test
    public void whenFactoryProvidedThenYamlPropertiesInjected() throws Exception {
        String data="111111";
        assertThat(yamlFooProperties.getSm2KeyPrivate()).isEqualTo("c:/usr/sm2_key/sm2_private_key.hex");
        assertThat(yamlFooProperties.getSm2KeyPublic()).isEqualTo("c:/usr/sm2_key/sm2_public_key.hex");
        String privateKey= yamlFooProperties.readFileToString(yamlFooProperties.getSm2KeyPrivate());
        String publicKey =yamlFooProperties.readFileToString(yamlFooProperties.getSm2KeyPublic());
        String encryptedData = SM2Utils.sm2Encrypt(data, publicKey);
        String decrypt = SM2Utils.sm2Decrypt(encryptedData, privateKey);
        assertEquals("111111",decrypt);
    }


    @Test
    public void testVerifyCertificate() {


    }


}
