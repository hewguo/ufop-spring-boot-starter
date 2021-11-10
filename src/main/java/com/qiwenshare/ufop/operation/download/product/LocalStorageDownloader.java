package com.qiwenshare.ufop.operation.download.product;

import com.qiwenshare.common.operation.FileOperation;
import com.qiwenshare.ufop.operation.download.Downloader;
import com.qiwenshare.ufop.operation.download.domain.DownloadFile;
import com.qiwenshare.ufop.util.IOUtils;
import com.qiwenshare.ufop.util.UFOPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@Component
public class LocalStorageDownloader extends Downloader {
    @Override
    public void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile) {
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        //设置文件路径
        File file = new File(UFOPUtils.getStaticPath() + downloadFile.getFileUrl());
        if (file.exists()) {


            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
                IOUtils.writeInputStreamToResponse(fis, httpServletResponse);
            } catch (FileNotFoundException e) {
                log.error("File not found, file: {} " , file.getPath());
            }

        }
    }

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        //设置文件路径
        File file = new File(UFOPUtils.getStaticPath() + downloadFile.getFileUrl());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;

    }
}
