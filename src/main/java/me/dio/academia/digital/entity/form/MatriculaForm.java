package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaForm {
  @NotBlank(message = "Preencha o campo corretamente.")
  @Positive(message = "O Id do aluno tem que ser positivo")
  private Long alunoId;

}
