package com.quack.containerdashboard.config;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.List;

@Configuration
public class DockerClient {
    @Value("${docker.socket.path}")
    private String dockerSocketPath;
    @Bean
    public com.github.dockerjava.api.DockerClient buildDockerClient(){
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig
                .createDefaultConfigBuilder();
        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")){
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    .withDockerTlsVerify(false);
        }

        DockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost()).build();

        return DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();
    }
}
