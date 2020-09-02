package com.myboot.controllers.Job;

import com.myboot.vo.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nansen
 * @create 2020/6/5 14:52
 */
@RestController
public class JobController {

    @GetMapping("getJobs")
    public Job getJobs(){
        Job job = new Job();
        return job;
    }
}
