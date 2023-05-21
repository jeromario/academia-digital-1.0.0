package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exception.BusinessException;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.mapper.AlunoMapper;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoMapper mapper;
    @Override
    public Aluno create(AlunoForm form) {
//        Aluno aluno  = new Aluno();
//        aluno.setNome(form.getNome());
//        aluno.setCpf(form.getCpf());
//        aluno.setBairro(form.getBairro());
//        aluno.setDataDeNascimento(form.getDataDeNascimento());

        Aluno aluno = mapper.toAluno(form);
        repository.save(aluno);
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matriculaRepository.save(matricula);
        return aluno;
    }

    @Override
    public Aluno get(Long id) {
        Optional<Aluno> byId = repository.findById(id);
        if (byId.isEmpty()){
            throw new BusinessException("Aluno não cadastrado.");
        }
        return byId.get();
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if (dataDeNascimento == null){
            return repository.findAll();
        }else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localDate);
        }


    }

    @Override
    public Aluno update(Long id,/*Aluno aluno*/AlunoUpdateForm formUpdate) {
        Optional<Aluno> alunoOptional = repository.findById(id);

        if (alunoOptional.isEmpty()){
            throw new BusinessException("Aluno não cadastrado.");
        }
//        aluno.get().setNome(formUpdate.getNome());
//        aluno.get().setBairro(formUpdate.getBairro());
//        aluno.get().setDataDeNascimento(formUpdate.getDataDeNascimento());
//        aluno.setNome(formUpdate.getNome());
//        aluno.setBairro(formUpdate.getBairro());
//        aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
        //Aluno aluno = mapper.toAluno(formUpdate);
        //aluno.setId(id);
        //aluno.setCpf(alunoOptional.get().getCpf());
        Aluno aluno = mapper.toAluno(formUpdate);
        aluno.setId(id);
        aluno.setCpf(alunoOptional.get().getCpf());


        return repository.save(aluno);
    }

    @Override
    //@Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
        Aluno aluno = repository.findById(id).get();
        return aluno.getAvaliacoes();

    }
}
