package com.shiro.test;

import java.security.Key;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;


public class AuthenticatorTest {
	private void login(String configFile){
		//1.��ȡSecurityManager������ʹ��ini�����ļ���ʼ��SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		//2.�õ�SecurityManagerʵ�����󶨸�SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3.�õ�subject�������û���/���������֤token
		Subject subject = SecurityUtils.getSubject();
		subject.checkPermission("system:user:*");
		UsernamePasswordToken upt = new UsernamePasswordToken("zhang","123");
		subject.login(upt);
		
		DefaultHashService hashService = new DefaultHashService();//Ĭ���㷨SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123"));//˽�Σ�Ĭ����
		hashService.setGeneratePublicSalt(true);//�Ƿ����ɹ���    Ĭ��false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//�������ɹ��Σ�Ĭ�Ͼ����
		hashService.setHashIterations(1);
		
		HashRequest hashRequest = new HashRequest.Builder()
						.setAlgorithmName("MD5")
						.setSource(ByteSource.Util.bytes("hello"))
						.setSalt(ByteSource.Util.bytes("123"))
						.setIterations(2)
						.build();
		
		String hex = hashService.computeHash(hashRequest).toHex();
		
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128);//����KEY����
		//����KEY
		Key key = aesCipherService.generateNewKey();
		String text = "hello";
		//����
		String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
		//����
		String decrptText = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
		Assert.assertEquals(text, decrptText);
		
	}
	
	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		//�õ�һ����ݺϼ����������realm��֤�ɹ��������Ϣ
		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	@Test
	public void testHasRole(){
		login("classpth:shiro-role.ini");
	}
	
	
}
