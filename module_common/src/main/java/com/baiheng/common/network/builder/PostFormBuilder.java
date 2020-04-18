package com.baiheng.common.network.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.baiheng.common.network.request.PostFormRequest;
import com.baiheng.common.network.request.RequestCall;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable {
    
    private List<FileInput> files = new ArrayList<>();
    
    @Override
    public RequestCall build() {
        
        return new PostFormRequest(url, tag, params, headers, files, id).build();
    }
    
    public PostFormBuilder files(String key, Map<String, File> files) {
        
        for (String fileName : files.keySet()) {
            this.files.add(new FileInput(key, fileName, files.get(fileName)));
        }
        return this;
    }
    
    public PostFormBuilder addFile(String key, String fileName, File file) {
        
        files.add(new FileInput(key, fileName, file));
        return this;
    }
    
    /**
     * File in the form of a form
     */
    public static class FileInput {
        public String key;
        public String fileName;
        public File file;
        
        public FileInput(String name, String fileName, File file) {
            
            this.key = name;
            this.fileName = fileName;
            this.file = file;
        }
        
        @Override
        public String toString() {
            
            return "FileInput{" + "key='" + key + '\'' + ", filename='" + fileName + '\'' + ", file=" + file + '}';
        }
    }
    
    @Override
    public OkHttpRequestBuilder params(Map<String, String> params) {
        
        this.params = params;
        return this;
    }
    
    @Override
    public OkHttpRequestBuilder addParams(String key, String value) {
        
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return this;
    }
}
