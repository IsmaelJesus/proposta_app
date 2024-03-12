package net.atos.propostaapp.controller;

import lombok.AllArgsConstructor;
import net.atos.propostaapp.dto.PropostaRequestDto;
import net.atos.propostaapp.dto.PropostaResponseDto;
import net.atos.propostaapp.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto){
        PropostaResponseDto response = propostaService.criar(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/(id)").
                buildAndExpand(response.getId()).toUri())
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> listarPropostas(){
        return ResponseEntity.ok(propostaService.obterProposta());
    }
}
