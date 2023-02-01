package com.example.fhdemo.cert;

import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.stream.Collectors;

public class SM2Utils {

    private static final String ENCODING = "UTF-8";
    private static SM2Engine.Mode sm2Mode = SM2Engine.Mode.C1C3C2;
    //获取一条SM2曲线参数
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    //构造domain参数
    private static ECDomainParameters ecDomainParameters =
            new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
    private static ECParameterSpec ecParameterSpec =
            new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());

    /**
     * sm2解密
     *
     * @param data   解密数据
     * @param pivKey 私钥
     * @return
     */
    public static String sm2Decrypt(String data, String pivKey) {
        String decryptData = "";
        try {
            BCECPrivateKey bcePivKey = getPrivatekeyFromD(new BigInteger(pivKey, 16));
            byte[] dataBs = HexUtils.fromHexString(data);
            decryptData = new String(_sm2Decrypt(sm2Mode, dataBs, bcePivKey), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptData;
    }

    /**
     * sm2加密
     *
     * @param data   解密数据
     * @param pubKey 公钥
     * @return
     */
    public static String sm2Encrypt(String data, String pubKey) throws Exception {
        BCECPublicKey bcePubKey = getPublickeyFromEncoded(HexUtils.fromHexString(pubKey));
        String encryptData = "";
        try {
            byte[] dataBs = data.getBytes(ENCODING);
            encryptData = HexUtils.toHexString(_sm2Encrypt(sm2Mode, dataBs, bcePubKey));
        } catch (Exception e) {
            throw new Exception("sm2加密异常");
        }
        return encryptData;
    }

    /**
     * sm2加密
     *
     * @param data 加密数据
     * @param key  公钥
     * @return
     */
    public static byte[] _sm2Encrypt(SM2Engine.Mode mode, byte[] data, PublicKey key) {
        BCECPublicKey localECPublicKey = (BCECPublicKey) key;
        ECPublicKeyParameters ecPublicKeyParameters =
                new ECPublicKeyParameters(localECPublicKey.getQ(), ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine(mode);
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sm2解密
     *
     * @param data 解密数据
     * @param key  私钥
     * @return
     */
    public static byte[] _sm2Decrypt(SM2Engine.Mode mode, byte[] data, PrivateKey key) {
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) key;
        ECPrivateKeyParameters ecPrivateKeyParameters =
                new ECPrivateKeyParameters(localECPrivateKey.getD(), ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine(mode);
        sm2Engine.init(false, ecPrivateKeyParameters);
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException("sm2解密失败", e);
        }
    }

    /**
     * 私钥签名
     */
    public static byte[] signByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), BouncyCastleProvider.PROVIDER_NAME);
        sig.initSign(privateKey);
        sig.update(data);
        byte[] ret = sig.sign();
        return ret;
    }

    /**
     * 公钥验签
     */
    public static boolean verifyByPublicKey(byte[] data, PublicKey publicKey, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), BouncyCastleProvider.PROVIDER_NAME);
        sig.initVerify(publicKey);
        sig.update(data);
        boolean ret = sig.verify(signature);
        return ret;
    }

    /**
     * 获取私钥对象
     *
     * @param d
     * @return
     */
    public static BCECPrivateKey getPrivatekeyFromD(BigInteger d) {
        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecParameterSpec);
        return new BCECPrivateKey("EC", ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    /**
     * 获取公钥对象
     *
     * @param encodedBytes
     * @return
     */
    public static BCECPublicKey getPublickeyFromEncoded(byte[] encodedBytes) {
        ECPublicKeySpec ecPublicKeySpec =
                new ECPublicKeySpec(x9ECParameters.getCurve().decodePoint(encodedBytes), ecParameterSpec);
        return new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public  String readFileToString(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = null;
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("sm2私钥文件不存在:" + path);
            }
            fis = new FileInputStream(file);
            return new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    //sm2生成公私钥
    public static void genKeyPair0() {
        try {
            // 获取一个椭圆曲线类型的密钥对生成器
            ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();

            //2.初始化生成器,带上随机数
            try {
                keyPairGenerator.init(new ECKeyGenerationParameters(ecDomainParameters, SecureRandom.getInstance("SHA1PRNG")));
            } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
            }

            //3.生成密钥对
            AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();
            //提取公钥点
            ECPoint ecPoint = ((ECPublicKeyParameters) asymmetricCipherKeyPair.getPublic()).getQ();
            //公钥前面的02或者03表示是压缩公钥,04表示未压缩公钥,04的时候,可以去掉前面的04 true=压缩
            String pubKey = Hex.toHexString(ecPoint.getEncoded(false));

            BigInteger privatekey = ((ECPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate()).getD();
            String priKey = privatekey.toString(16);

            System.out.println(pubKey);
            System.out.println(priKey);
        } catch (Exception e) {
        }
    }

    public static void main(String[] args)
            throws Exception {
        //String str = strTo16("20210420d9i720H219OlasuN");
        //String sessionid = str.substring(str.length()-16);
        //System.out.println(sessionid);
        //SM2Utils.loadingTheKeys("G:\\pub.hex", "G:\\piv.hex");
        //byte[] bytes_key = new byte[]{1, 2, 3, 4};
        //String nonce = org.apache.commons.codec.binary.Hex.encodeHexString(bytes_key);
        String s = SM2Utils.sm2Encrypt("fei fu sm2 test", "04155e413ac977cb11d7ab6eddbfd8371fc75237c84f4e6ed8fcd664bc04a701a75922dab4bf0e2f6eed6517e52bd67e908c9c9aa9d1a59b0d3f90a5250ecda93d");
        System.out.println(s);
        //String s = HexUtils.ByteToHex(enBytes);
        //System.out.println(s);
        String enBytes2 = "0404ebfc718e8d1798620432268e77feb6415e2ede0e073c0f4f640ecd2e149a73e858f9d81e5430a57b36daab8f950a3c64e6ee6a63094d99283aff767e124df06d3ce04aa59f7ad74bc65f36a784403c37531d2f955cc42049666bb86de856e644dea6f97d6320cd09dfb036b9cc8544ec09bb3313c3fe6e";
        String decrypt = SM2Utils.sm2Decrypt(enBytes2, "7c6acee239fcd5267f6dcaf715f305213685909faa733eccb16cef35e0afd951");
        System.out.println(decrypt);
        //genKeyPair0();
    }

    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
}
