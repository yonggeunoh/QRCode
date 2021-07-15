import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCreate{
/*
    // 맴버 변수
    private String codeurl;

    // 생성자
    public QRCreate(String url) {
        this.codeurl = url;
    }
  */
    public static void main(String[] args) {

        String param_url = "";

        if (args.length == 0){
            param_url = "http://ecos.bok.or.kr";
        }else{
            param_url = args[0];
        }

        try {
            File file = null;

            file = new File("D:\\APP\\JAVA\\qrcode");
            if(!file.exists()) {
                file.mkdirs();
            }

            // 코드인식시 링크걸 URL주소
            String url =  new String(param_url.getBytes("UTF-8"), "ISO-8859-1");
           // QRCreate q = new QRCreate(url);

            // 큐알코드 바코드 생상값
            int qrcodeColor =   0xFF2e4e96;

            // 큐알코드 배경색상값
            int backgroundColor = 0xFFFFFFFF;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // 3,4번째 parameter값 : width/height값 지정
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE,200, 200);

            //MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

            // ImageIO를 사용한 바코드 파일쓰기
            Calendar cal = Calendar.getInstance();
            String timeStamp =  String.format("%04d%02d%02d_%02d%02d%02d"
                              , cal.get(Calendar.YEAR)
                              , (cal.get(Calendar.MONTH) + 1)
                              , cal.get(Calendar.DAY_OF_MONTH)
                              , cal.get(Calendar.HOUR_OF_DAY)
                              , cal.get(Calendar.MINUTE)
                              , cal.get(Calendar.SECOND)
                             );

            ImageIO.write(bufferedImage, "png", new File("./qrcode_" + timeStamp +"_"+param_url.replace("http://", "")+".png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}