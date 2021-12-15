package com.gestao.projeto.model.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.gestao.projeto.model.Empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@NotBlank
	@Size(max = 15)
	private String telefone;
	
	
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank
    private Empresa empresa;
 
}
