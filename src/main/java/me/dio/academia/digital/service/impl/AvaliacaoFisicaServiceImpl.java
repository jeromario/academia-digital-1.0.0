package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exception.BusinessException;
import me.dio.academia.digital.mapper.AvaliacaoMapper;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository repository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoMapper mapper;
    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Optional<Aluno> byId = alunoRepository.findById(form.getAlunoId());
        if (byId.isEmpty()){
            throw new BusinessException("Aluno não cadastrado.");
        }
        Aluno aluno = byId.get();
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());
        return repository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        Optional<AvaliacaoFisica> byId = repository.findById(id);
        if (byId.isEmpty()){
            throw new BusinessException("Avaliação não encontrada");
        }
        return byId.get();
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        Optional<AvaliacaoFisica> byId = repository.findById(id);
        if (byId.isEmpty()){
            throw new BusinessException("Avaliação não encontrada.");
        }
        AvaliacaoFisica avaliacaoFisica = mapper.toAvaliacaoFisica(formUpdate);
        avaliacaoFisica.setId(id);
        avaliacaoFisica.setAluno(byId.get().getAluno());
//        AvaliacaoFisica avaliacaoFisica = byId.get();
//        avaliacaoFisica.setPeso(formUpdate.getPeso());
//        avaliacaoFisica.setAltura(formUpdate.getAltura());
        return repository.save(avaliacaoFisica);
    }

    @Override
    public void delete(Long id) {
        Optional<AvaliacaoFisica> byId = repository.findById(id);
        if (byId.isEmpty()){
            throw new BusinessException("Avaliação não encontrada.");
        }else {
            repository.deleteById(id);
        }


    }
}
