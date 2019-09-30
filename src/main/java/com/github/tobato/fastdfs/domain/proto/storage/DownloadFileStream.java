package com.github.tobato.fastdfs.domain.proto.storage;import java.io.BufferedInputStream;import java.io.IOException;import java.io.InputStream;import java.io.OutputStream;import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载回调方法,1M1M读取，防止下载时内存溢出
 * 同时不要讲流返回出去，再进行操作，不然并发下载情况下会有各种问题
 *
 * @author xulb
 */
public class DownloadFileStream implements DownloadCallback<BufferedInputStream> {

	private HttpServletResponse response;

    public DownloadFileStream(HttpServletResponse response) {
		this.response=response;
	}

	/**
     * 文件接收处理
	 * @return 
     */

	@Override
	public BufferedInputStream recv(InputStream ins) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(ins);
		// 实现文件下载
		byte[] buffer = new byte[1024];
		OutputStream os=null;
		try {
			 os = response.getOutputStream();
			int i = bufferedInputStream.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bufferedInputStream.read(buffer);
			}
		} catch (Exception e) {
			
			throw new IOException("文件下载失败!",e);
		}finally {
			bufferedInputStream.close();
			os.flush();
			os.close();
			
		}
		return null;
		
    }

}
