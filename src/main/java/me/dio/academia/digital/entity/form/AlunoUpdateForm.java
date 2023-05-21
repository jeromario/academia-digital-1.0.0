package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUpdateForm {
  @NotBlank(message = "Preencha o campo corretamente.")
  @Size(min = 3,max = 30,message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String nome;
  @NotBlank(message = "Preencha o campo corretamente.")
  @Size(min = 3,max = 30,message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String bairro;
  @NotBlank(message = "Preencha o campo corretamente.")
  @Past(message = " Data '${validatedValue}' é invalido")
  private LocalDate dataDeNascimento;
}
