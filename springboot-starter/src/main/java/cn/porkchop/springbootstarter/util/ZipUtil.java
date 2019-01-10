package cn.porkchop.springbootstarter.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.List;


public class ZipUtil {
    private static final int BUFFER_SIZE = 4096;

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[2 * 1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    LogUtil.error("",e);
                }
            }
        }
    }

    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
    	ZipFile zipFile = null;  
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        zipFile = new ZipFile(zipFilePath, "GBK");  
        Enumeration<ZipEntry> zipEntries = zipFile.getEntries();
        // zipIn = new ZipInputStream(new FileInputStream(zipFilePath),Charset.forName("gbk"));
        //ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        //while (entry != null) {
        ZipEntry entry;
        while(zipEntries.hasMoreElements()){
        	entry = (ZipEntry) zipEntries.nextElement();
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                //extractFile(entry, filePath);
            	BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));  
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                byte[] bytesIn = new byte[BUFFER_SIZE];
                int read = 0;
                while ((read = bis.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                }
                bos.close();
                bis.close();
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
        }
        zipFile.close();
    }

    private static void extractFile(ZipEntry entry, String filePath) throws IOException {
    	
    }
}
