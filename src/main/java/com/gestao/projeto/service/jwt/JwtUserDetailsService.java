package com.gestao.projeto.service.jwt;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestao.projeto.model.ERole;
import com.gestao.projeto.model.Role;
import com.gestao.projeto.model.User;
import com.gestao.projeto.model.request.SignupRequest;
import com.gestao.projeto.repository.RoleRepository;
import com.gestao.projeto.repository.UserRepository;


@Service
public class JwtUserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;

	public User save(@Valid SignupRequest signUpRequest) throws Exception {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new Exception("Usuário já existente!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new Exception("E-mail já existente!");
		}

		return createdUser(signUpRequest);
	}
	
	public void loadUser(@Valid SignupRequest signUpRequest) {
		if (!(userRepository.existsByUsername(signUpRequest.getUsername()) || userRepository.existsByEmail(signUpRequest.getEmail()))) {
			createdUser(signUpRequest);
		}
	}
	
	public User createdUser(@Valid SignupRequest signUpRequest) {
				User user = new User(signUpRequest.getNome(),
									 signUpRequest.getTelefone(),
									 signUpRequest.getUsername(), 
									 signUpRequest.getEmail(),
									 encoder.encode(signUpRequest.getPassword()));

				Set<String> strRoles = signUpRequest.getRole();
				Set<Role> roles = new HashSet<>();

				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "ROLE_ADMIN":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						case "ROLE_MODERATOR":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
						}
					});
				}

				user.setRoles(roles);
				user.setEmpresa(signUpRequest.getEmpresa());
				return userRepository.save(user);
	}

}
