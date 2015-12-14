package com.walmart.services.checkout.testng.buynowsuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.jce.provider.BouncyCastleProvider;



public class EncryptionUtil {

	
	/**
	 * Get public key from the contents of the above key file.
	 * Note: Assuming the whole file is read and available as a string in contents
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static PublicKey readPublicKey(String contents) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		// Strip the header, footer and new lines
		String stripped = contents.replaceAll("\n","").replaceAll("-----BEGIN PUBLIC KEY-----", "").replaceAll("-----END PUBLIC KEY-----", "");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(stripped));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}
	
	
	/**
	 * Encrypt and base64 encode card number
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String encryptCardNumber(String cardNumber, PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(cardNumber.getBytes("UTF-8")));
	}
	
	
	/**
	 * Read public key using Bouncy Castle library
	 * @throws IOException 
	 */
	public static PublicKey readPublicKey(File file) throws IOException {
		Security.addProvider(new BouncyCastleProvider());
		@SuppressWarnings("resource")
		PEMReader pemReader = new PEMReader(new FileReader(file));
		return (PublicKey)pemReader.readObject();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		
		String cardNumber = "4888940143923951";
		String publicKeyString = "-----BEGIN PUBLIC KEY-----"+
										"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqE5V8O7AhPiHzjEMCSpp"+
										"RfMVVVCRiIXSFFkqBek2oF5UiLSp79knXFXIZW8cbXcnXmjCfjmxnt7ZpZoGCj50"+
										"sJMTNntt4TmM3U9yhm+6bQaekCQjzmwV2OihkmM0vxB7yTBEBkq5oi7MP2ksRJNC"+
										"W7k/slYQkkbmForYrK1HXYbNZtI+PNEOk2HoNGED5qzYZKSPjdT2wkSnpUp2BXDE"+
										"VqZR/jjZkumifW0iu8PhqcYQvE+VrxCm2GWqgeXet50N3mhTZkhDQoAr4b+GCEmD"+
										"ticG6xnmQNbUYxgqGmlTFVLmqB74WpBbWz/kRF6MAq7YpiOZ4yoeQ65hMZx/0ASK"+
										"YQIDAQAB"+
										"-----END PUBLIC KEY-----";
		PublicKey publicKey = readPublicKey( publicKeyString);
		/**
		 * cjln+msVIlSJTmNN9i3gqMlwWV0diWNySMjSgSzetK3LTp80/IYNPF21wjgBBjaPtNnIzhrHcx+G/kejkN20MVYrrKG5goe6uoiSuPl/RbMv1OKon/b5Y+PKWB1HY9xiwLD1haDH939mi0lxbfpnDgULQR6MoaqRjn7hvaZIDjpb80R6aGP6Q4IpfmVcLvhfrBPmzc4tt2Zywx1CT3TxcuHZrujD5rl8L1vrCiqZFMvV9Z/h/r07HSHCsU/x+qUZDEb5B73qm68yZ9bS2dcJGw7l7grK+PFfRc66uSYcneWRHKWs8uhw9XiVuJXmxTUT5dbvHD8MZBVMS8XRPfeVUw==",
		 */
		
		/**
		 * cagsrzTlb4jOXQk+0670KoK8LLpz8+eeXxOylxmR0iP62JjzBB7iGcxB6miXDSd6iirXdq6rCbFY1P+FLd5bwZDDnQKyU1D61qMaeDqZrEaXx5n7alAqArP0Jnf4NbGzlMx3N9uK0rxl4DYdSPiUn5g3/ry55IDgYJBFunNcnYzBw36YxfThM8yojzHuXqebr9/XXePRjfOOs/VcWJGwOEFy8foWnk8ecfxao0HapPzq88z6rSWXM0fgJimZ+lyq9jB1UHqvpJyyTIccr2uzEi3A7bZYFaI4lImyV81efzUWzRlIPAvI9DtadIdMYCpDk7tyjlEz9u6UZWm2++NLZQ==
		 */
				 
		
		System.out.println("Encrypted Card Number {}"+ encryptCardNumber(cardNumber,publicKey));
		
//		File file = new File("/Users/satmuri/workspace/master/checkout/checkoutservice/src/test/resources/publickey.config");
//		
//		PublicKey publicKeyFromFile = readPublicKey(file);
//		System.out.println("Encrypted Card Number .. Key Read from file {}"+ encryptCardNumber(cardNumber,publicKeyFromFile));
//		
	}
	 
}
