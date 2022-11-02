package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;


    @PostMapping("/{uid}/addProblem/{mid}")
    public Problem addProblem(@RequestBody Problem problem, @PathVariable int uid, @PathVariable int mid){
        return problemService.saveProblem(problem, uid, mid);
    }

    @GetMapping("/viewProblem/{id}")
    public Problem viewProblem(@PathVariable int id){
        return problemService.displayProblem(id);
    }


}