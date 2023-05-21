package me.dio.academia.digital.controller;

import lombok.RequiredArgsConstructor;
import me.dio.academia.digital.dto.AlunoResponse;
import me.dio.academia.digital.dto.AvaliacaoResponse;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exception.BusinessException;
import me.dio.academia.digital.mapper.AlunoMapper;
import me.dio.academia.digital.mapper.AvaliacaoMapper;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {
    @Autowired
    private AlunoServiceImpl service;
    @Autowired
    private AlunoMapper mapper;
    @Autowired
    private AvaliacaoMapper avaliacaoMapper;


    @GetMapping
    public ResponseEntity <List<AlunoResponse>>getAll(@RequestParam(value = "dataDeNascimento", required = false)
                             String dataDeNascimento){
        List<Aluno> all = service.getAll(dataDeNascimento);
        return ResponseEntity.ok(mapper.toAlunoResponseList(all));
    }
    @PostMapping
    public ResponseEntity<AlunoResponse> create(@Valid @RequestBody AlunoForm form){
        Aluno aluno = service.create(form);
        return ResponseEntity.ok(mapper.toAlunoResponse(aluno));
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity<List<AvaliacaoResponse>> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        List<AvaliacaoFisica> allAvaliacaoFisicaId = service.getAllAvaliacaoFisicaId(id);
        return ResponseEntity.ok(avaliacaoMapper.toAvaliacaoResponseList(allAvaliacaoFisicaId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> get(@PathVariable Long id){
        if (service.get(id) == null){
            return ResponseEntity.notFound().build();
        }
        Aluno aluno = service.get(id);
        return ResponseEntity.ok(mapper.toAlunoResponse(aluno));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse>update(@PathVariable Long id, @RequestBody AlunoUpdateForm form){
        Aluno update = service.update(id, form);
        return ResponseEntity.ok(mapper.toAlunoResponse(update));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping
//    public Aluno create(@RequestBody AlunoForm form){
//        return service.create(form);
//    }
}
