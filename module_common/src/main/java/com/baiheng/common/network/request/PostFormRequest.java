package com.baiheng.common.network.request;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baiheng.common.network.OkHttpUtils;
import com.baiheng.common.network.builder.PostFormBuilder;
import com.baiheng.common.network.callback.Callback;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class PostFormRequest extends OkHttpRequest {
    
    private List<PostFormBuilder.FileInput> files;
    
    public PostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers,
            List<PostFormBuilder.FileInput> files, int id) {
        
        super(url, tag, params, headers, id);
        this.files = files;
    }
    
    /**
     *
     * @return RequestBody waiting to be submitted
     */
    @Override
    protected RequestBody buildRequestBody() {
        
        if (files == null || files.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            FormBody formBody = builder.build();
            return formBody;
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            addParams(builder);
            
            for (int i = 0; i < files.size(); i++) {
                PostFormBuilder.FileInput fileInput = files.get(i);
                // Constructs the file RequestBody
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.fileName)),
                        fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.fileName, fileBody);
            }
            return builder.build();
        }
    }
    
    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        
        if (callback == null)
            return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody,
                new CountingRequestBody.Listener() {
                    @Override
                    public void onRequestProgress(final long bytesWritten, final long contentLength) {
                        
                        OkHttpUtils.getInstance().getDelivery().execute(new Runnable() {
                            @Override
                            public void run() {
                                
                                callback.inProgress(bytesWritten * 1.0f / contentLength, contentLength, id);
                            }
                        });
                        
                    }
                });
        return countingRequestBody;
    }
    
    @Override
    protected Request buildRequest(RequestBody requestBody) {
        
        return builder.post(requestBody).build();
    }
    
    /**
     * Determine the data type
     * 
     * @param path
     * @return
     */
    private String guessMimeType(String path) {
        
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream"; // Binary stream data
        }
        return contentTypeFor;
    }
    
    /**
     * add RequestBody data(file in the form of a form)
     * 
     * @param builder
     */
    private void addParams(MultipartBody.Builder builder) {
        
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Dispositon", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, Objects.requireNonNull(params.get(key))));
            }
        }
    }
    
    /**
     * add RequestBody data(key pair)
     * 
     * @param builder
     */
    private void addParams(FormBody.Builder builder) {
        
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, Objects.requireNonNull(params.get(key)));
            }
        }
    }
}
