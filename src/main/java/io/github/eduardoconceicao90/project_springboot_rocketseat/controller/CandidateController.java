package io.github.eduardoconceicao90.project_springboot_rocketseat.controller;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.ErrorMessageDTO;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Job;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.dto.ProfileCandidateDTO;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.useCases.ProfileCandidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Candidato", description = "Informações do candidato")
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidate profileCandidate;

    @Operation(summary = "Cadastrar novo candidato",
            description = "Essa função é responsável por cadastrar um novo candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Candidate.class)
            )),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate){

        try{
            var result = candidateService.create(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "Perfil do candidato",
            description = "Essa função é responsável por retornar o perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProfileCandidateDTO.class)
            )),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDTO.class)
            ))
    })
    @SecurityRequirement(name = "jwt_auth")
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping
    public ResponseEntity<Object> get(HttpServletRequest request){

        var idCandidate = request.getAttribute("candidate_id");

        try{
            var profile = profileCandidate.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "Listagem de vagas disponíveis para o candidato",
            description = "Essa função é responsável por listar as vagas disponíveis para o candidato, baseada do filtro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Job.class))
            ))
    })
    @SecurityRequirement(name = "jwt_auth")
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/job")
    public ResponseEntity<List<Job>> findJobByFilter(@RequestParam String filter){
        var list = candidateService.findByDescription(filter);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Inscrição do candidato em uma vaga",
            description = "Essa função é responsável por realizar a inscrição do candidato em uma vaga")
    @SecurityRequirement(name = "jwt_auth")
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/job/apply")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob){
        var idCandidate = request.getAttribute("candidate_id");

        try{
            var result = candidateService.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
