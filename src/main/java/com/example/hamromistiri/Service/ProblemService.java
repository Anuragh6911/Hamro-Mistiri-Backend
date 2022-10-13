package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public Problem saveProblem(Problem problem ){
        return problemRepository.save(problem);
    }

    public Problem displayProblem(int id){
        return problemRepository.findById(id).orElse(null);
    }
}
