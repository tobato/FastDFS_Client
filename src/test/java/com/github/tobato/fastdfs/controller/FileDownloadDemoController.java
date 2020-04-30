package com.github.tobato.fastdfs.controller;

import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author MagakiReimu
 * @version 1.0
 */
@RestController
@RequestMapping("/download")
public class FileDownloadDemoController {

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @GetMapping("/demo")
    public void downloadDemo(@RequestHeader(value = HttpHeaders.RANGE, required = false) String rangeHeader,
                             HttpServletResponse response) {
        // 以下数据，请从自己的开发系统中获取
        String fileName = "abc.txt";
        String group = "group1";
        String path = "xxxxx";
        long fileSize = 0L;

        // 分段下载计算参数
        long rangeStart = 0L;
        long rangeEnd = 0L;
        long downloadSize = 0L;
        long contentLength = fileSize;

        // 获取分段下载请求
        List<HttpRange> httpRangeList = HttpRange.parseRanges(rangeHeader);
        if (httpRangeList.size() == 0) {
            // 无分段下载请求，下载为全量下载
            // 调用downloadFile下载整个文件, 调用接口为只有group, path参数时
            // 下载接口统一, 保持二次调用中rangeStart, downloadSize为0
            // 普通下载状态码200
            response.setStatus(HttpStatus.OK.value());
        } else if (httpRangeList.size() == 1) {
            // 分段下载只解析第一个，一般请求只包含一个
            HttpRange httpRange = httpRangeList.get(0);
            // 请求起始字节
            rangeStart = httpRange.getRangeStart(fileSize);
            // 请求结束字节
            rangeEnd = httpRange.getRangeEnd(fileSize);
            // 请求分段长度
            contentLength = rangeEnd - rangeStart + 1L;
            // 请求下载长度
            downloadSize = contentLength;
            // Content-Range: bytes [unit first byte pos] - [last byte pos] / [entity length]
            String contentRange = String.format("bytes %d-%d/%d", rangeStart, rangeEnd, fileSize);
            response.addHeader(HttpHeaders.CONTENT_RANGE, contentRange);
            // 分段下载状态码206
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
        } else {
            // 协议支持一个请求包含多段下载, Demo不做多段解析
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        // Content-Type
        // 方式一：写死Content-Type
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
        // 方式二：根据文件名猜测Content-Type
        contentType = MediaType.parseMediaType(URLConnection.guessContentTypeFromName(fileName));
        response.setContentType(contentType.toString());
        // Content-Disposition
        //@formatter:off
        ContentDisposition contentDisposition = ContentDisposition
                                                                .builder("form-data")
                                                                .name("attachment")
                                                                .filename(fileName, StandardCharsets.UTF_8)
                                                                .build();
        //@formatter:on
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        // 本次下载大小
        response.setContentLengthLong(contentLength);
        // 支持分段下载
        response.addHeader(HttpHeaders.ACCEPT_RANGES, "bytes");

        // 根据上方计算值，下载区间数据
        defaultGenerateStorageClient.downloadFile(group, path, rangeStart, downloadSize,
            inputStream -> {
                IOUtils.copy(inputStream, response.getOutputStream());
                return null;
            });
    }
}
