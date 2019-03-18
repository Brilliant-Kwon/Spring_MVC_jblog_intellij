package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@Service
public class FileUploadService {
    private static final String SAVE_PATH = "/upload";

    //저장 메서드
    public String store(MultipartFile mFile) {
        String saveFilename = "";

        try {
            String originalFilename = mFile.getOriginalFilename();//확장자를 받아와야 하므로 원본 파일 명 받아옴
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            Long size = mFile.getSize();
            saveFilename = getSaveFilename(extName);
            System.out.println("원본 파일 명 : " + originalFilename);
            System.out.println("확장자명 : " + extName);
            System.out.println("저장 파일 명 : " + saveFilename);

            // 멀티파트 파일 저장 -> 별도 메서드
            writeFile(mFile, saveFilename);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return saveFilename;
    }

    //저장 이름 정하는 메서드
    public String getSaveFilename(String ext) {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.getTimeInMillis()) + ext.toLowerCase();
    }

    //실제 파일 저장 메서드
    public void writeFile(MultipartFile mFile, String saveFilename) throws IOException {
        byte[] fileData = mFile.getBytes();

        FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/"+saveFilename);
        fos.write(fileData);
        fos.close();

    }
}
