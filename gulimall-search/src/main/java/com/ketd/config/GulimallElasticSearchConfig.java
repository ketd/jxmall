package com.ketd.config;

import co.elastic.clients.elasticsearch.*;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.*;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.*;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.AbstractElasticsearchTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.*;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.config
 * @Author: ketd
 * @CreateTime: 2024-04-09  17:34
 */
@Configuration
public class GulimallElasticSearchConfig{


    @Bean
    public  ElasticsearchClient setRestClient() throws Exception{

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
            new UsernamePasswordCredentials("elastic","sacred624?"));
    Resource resource = new ClassPathResource("certs/http_ca.crt");
    CertificateFactory factory = CertificateFactory.getInstance("X.509");
    Certificate trustedCa;
    try(InputStream is = resource.getInputStream();)
    {
        trustedCa = factory.generateCertificate(is);
    }

    KeyStore trustStore = KeyStore.getInstance("pkcs12");
    trustStore.load(null,null);
    trustStore.setCertificateEntry("ca",trustedCa);
    SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustStore, null);
    final SSLContext sslContext = sslContextBuilder.build();
    RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https")).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
        @Override
        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .setDefaultCredentialsProvider(credentialsProvider);
        }
    });
    RestClient restClient = builder.build();
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    ElasticsearchClient client = new ElasticsearchClient(transport);
    //ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);
    return client;
    //transport.close();
    }
}
