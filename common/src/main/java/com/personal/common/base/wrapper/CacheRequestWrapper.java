package com.personal.common.base.wrapper;

/**
 * @Title: CacheRequestWrapper.class
 * @Package: org.ssm.zhb.common.basic.wrapper
 * @Descriptiom: HttpServletQuestWrapper实现类
 * @author: zhenghanbin
 * @date 2018/3/13 11:52
 */

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CacheRequestWrapper extends HttpServletRequestWrapper {

    private byte[] buffer;
    public CacheRequestWrapper(HttpServletRequest request) throws IOException {
        super( request );
        InputStream is = request.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buff[] = new byte[ 1024 ];
        int read;
        while( ( read = is.read( buff ) ) > 0 ) {
            baos.write( buff, 0, read );
        }
        this.buffer = baos.toByteArray();
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new BufferedServletInputStream( this.buffer );
    }

    class BufferedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream inputStream;
        public BufferedServletInputStream(byte[] buffer) {
            this.inputStream = new ByteArrayInputStream( buffer );
        }
        @Override
        public int available() throws IOException {
            return inputStream.available();
        }
        @Override
        public int read() throws IOException {
            return inputStream.read();
        }
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return inputStream.read( b, off, len );
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
