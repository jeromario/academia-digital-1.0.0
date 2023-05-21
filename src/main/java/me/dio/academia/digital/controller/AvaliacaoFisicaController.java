package me.dio.academia.digital.controller;

import me.dio.academia.digital.dto.AvaliacaoResponse;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.mapper.AvaliacaoMapper;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;
    @Autowired
    private AvaliacaoMapper mapper;

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> create(@RequestBody AvaliacaoFisicaForm form){
        AvaliacaoFisica avaliacaoFisica = service.create(form);
        AvaliacaoResponse avaliacaoResponse = mapper.toAvaliacaoResponse(avaliacaoFisica);
        return ResponseEntity.ok(avaliacaoResponse);
    }
    @GetMapping
    public ResponseEntity<List<AvaliacaoResponse>>getAll(){
        List<AvaliacaoFisica> all = service.getAll();
        if (all.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<AvaliacaoResponse> avaliacaoResponses = mapper.toAvaliacaoResponseList(all);
        return ResponseEntity.ok(avaliacaoResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse>get(@PathVariable Long id){
        AvaliacaoFisica avaliacaoFisica = service.get(id);
        if (avaliacaoFisica == null){
            return ResponseEntity.notFound().build();
        }
        AvaliacaoResponse avaliacaoResponse = mapper.toAvaliacaoResponse(avaliacaoFisica);
        return ResponseEntity.ok(avaliacaoResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse>update(@PathVariable Long id, @RequestBody AvaliacaoFisicaUpdateForm updateForm){
        AvaliacaoFisica update = service.update(id, updateForm);
        AvaliacaoResponse avaliacaoResponse = mapper.toAvaliacaoResponse(update);
        return ResponseEntity.ok(avaliacaoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
