package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exception.BusinessException;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository repository;
//    @Autowired
//    private AlunoRepository alunoRepository;
//    @Override
//    public Matricula create(MatriculaForm form) {
//        Matricula matricula = new Matricula();
//        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();
//        matricula.setAluno(aluno);
//        return repository.save(matricula);
//
//    }

    @Override
    public Matricula get(Long id) {
        Optional<Matricula> optionalMatricula = repository.findById(id);
        if (optionalMatricula.isEmpty()){
            throw new BusinessException("Matricula não encontrada");
        }
        return optionalMatricula.get();
    }


    @Override
    public List<Matricula> getAll(String bairro) {

        if (bairro==null){
            return repository.findAll();
        }else{
            return repository.findByAlunoBairro(bairro);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
