package com.baiheng.common.network.builder;


import com.baiheng.common.network.request.PostFormRequest;
import com.baiheng.common.network.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* author：zhewen
* description：
* date： 8/29/2019
* version：
*/
public class PostFormBuilder extends OkHttpRequestBuilder implements HasParamsable {

    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, files,id).build();
    }

    public PostFormBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    @Override
    public OkHttpRequestBuilder params(Map<String, String> params) {
        return null;
    }

    @Override
    public OkHttpRequestBuilder addParams(String key, String value) {
        return null;
    }
}
