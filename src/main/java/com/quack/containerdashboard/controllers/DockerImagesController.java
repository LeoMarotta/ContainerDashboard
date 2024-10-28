package com.quack.containerdashboard.controllers;

import com.quack.containerdashboard.services.DockerService;
import com.github.dockerjava.api.model.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class DockerImagesController {
    private final DockerService dockerService;

    public DockerImagesController(DockerService dockerService){
        this.dockerService = dockerService;
    }

    @GetMapping("/filter")
    public List<Image> listImages(@RequestParam(required = false, defaultValue = "image-") String containerName){
        return dockerService.filterImages(containerName);
    }


}
