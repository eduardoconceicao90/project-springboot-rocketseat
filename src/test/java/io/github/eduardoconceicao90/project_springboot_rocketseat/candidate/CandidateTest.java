package io.github.eduardoconceicao90.project_springboot_rocketseat.candidate;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.JobNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.JobRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.service.CandidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CandidateTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            candidateService.execute(null, null);
        } catch (Exception e) {
            Assertions.assertEquals(UserNotFoundException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new Candidate();
        candidate.setId(idCandidate);

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            candidateService.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertEquals(JobNotFoundException.class, e.getClass());
        }
    }

}
