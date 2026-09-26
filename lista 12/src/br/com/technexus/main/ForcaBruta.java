package br.com.technexus.main;

import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class ForcaBruta {
    public static void main(String[] args) throws Exception {
        String encryptedB64 = "U2FsdGVkX189CvKETNa+k2wHIMpbCwNk7HKB3nBRzOD9bBaPt2nFMCdElvKoRfTmmqVv41Trh37ORXFWRVNOX3"
                + "vpgPHULkkaoyh9DfmzrGBXkGnu/SJfQkGuU08zbgMQNSwCGTwoIHkUMzRFQELN0Q==";
        encryptedB64 = encryptedB64.replaceAll("\\s", "");

        byte[] dados = Base64.getDecoder().decode(encryptedB64);
        byte[] salt = Arrays.copyOfRange(dados, 8, 16);
        byte[] textoCifrado = Arrays.copyOfRange(dados, 16, dados.length);

        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";
        System.out.println("Iniciando ataque de força bruta no servidor da TechNexus...");
        long startTime = System.currentTimeMillis();

        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                for (char c3 : charset.toCharArray()) {
                    String testPass = "lam" + c1 + c2 + c3;
                    try {
                        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        PBEKeySpec spec = new PBEKeySpec(testPass.toCharArray(), salt, 1000, 48 * 8);
                        byte[] keyAndIv = factory.generateSecret(spec).getEncoded();

                        byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                        byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
                        byte[] resultBytes = cipher.doFinal(textoCifrado);
                        String result = new String(resultBytes);

                        if (result.contains("http")) {
                            long endTime = System.currentTimeMillis();
                            System.out.println("\nSUCESSO! A criptografia foi quebrada!");
                            System.out.println("Senha encontrada: " + testPass);
                            System.out.println("Link revelado: " + result.trim());
                            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
                            return;
                        }
                    } catch (Exception e) {
                        // senha incorreta, tenta a próxima combinação
                    }
                }
            }
        }
        System.out.println("\nForça bruta concluída. Senha não encontrada.");
    }
}
