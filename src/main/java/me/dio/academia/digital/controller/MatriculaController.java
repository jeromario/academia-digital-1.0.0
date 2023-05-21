package me.dio.academia.digital.controller;

import me.dio.academia.digital.dto.MatriculaResponse;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.mapper.AlunoMapper;
import me.dio.academia.digital.mapper.MatriculaMapper;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaServiceImpl service;
    @Autowired
    private MatriculaMapper mapper;

    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> getAll(@RequestParam(value = "bairro", required = false)String bairro){
        List<Matricula> matriculas = service.getAll(bairro);
        if (matriculas.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<MatriculaResponse> matriculaResponses = mapper.toMatriculaResponseList(matriculas);
        return ResponseEntity.ok(matriculaResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponse>get(@PathVariable Long id){
        Matricula matricula = service.get(id);
        if (matricula == null){
            return ResponseEntity.notFound().build();
        }
        MatriculaResponse matriculaResponse = mapper.toMatriculaResponse(matricula);
        return ResponseEntity.ok(matriculaResponse);
    }

//    @PostMapping
//    public Matricula create( @RequestBody MatriculaForm form){
//        return service.create(form);
//    }
}
