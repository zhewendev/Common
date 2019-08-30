package com.baiheng.common.network.request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
* author：zhewen
* description：
* date： 8/29/2019
* version：
*/
public class CountingRequestBody extends RequestBody {

    private RequestBody mDelegate;
    private Listener mListener;

    private CountingSink mCountingSink;

    public CountingRequestBody(RequestBody delegate, Listener listener) {
        this.mDelegate = delegate;
        this.mListener = listener;
    }

    @Override
    public MediaType contentType()
    {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength()
    {
        try
        {
            return mDelegate.contentLength();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException
    {

        mCountingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(mCountingSink);

        mDelegate.writeTo(bufferedSink);

        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink
    {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate)
        {
            super(delegate);
        }

        /**
         * exposure upload progress
         * @param source
         * @param byteCount
         * @throws IOException
         */
        @Override
        public void write(Buffer source, long byteCount) throws IOException
        {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            mListener.onRequestProgress(bytesWritten, contentLength());
        }

    }

    public interface Listener {
        /**
         * Exposure upload progress
         * @param bytesWritten the byte size has been upload
         * @param contentLength total byte size
         */
        void onRequestProgress(long bytesWritten, long contentLength);
    }
}
