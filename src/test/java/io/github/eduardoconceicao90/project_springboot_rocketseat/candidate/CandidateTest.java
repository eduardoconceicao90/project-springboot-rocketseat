package io.github.eduardoconceicao90.project_springboot_rocketseat.candidate;

import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.JobNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.exception.UserNotFoundException;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.ApplyJob;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Candidate;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.Job;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.ApplyJobRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.CandidateRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.repository.JobRepository;
import io.github.eduardoconceicao90.project_springboot_rocketseat.service.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            candidateService.execute(null, null);
        } catch (Exception e) {
            assertEquals(UserNotFoundException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new Candidate();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            candidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertEquals(JobNotFoundException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJob.builder()
                                        .candidate(Candidate.builder().id(idCandidate).build())
                                        .job(Job.builder().id(idJob).build())
                                        .build();

        var applyJobCreated = ApplyJob.builder()
                                        .id(UUID.randomUUID())
                                        .build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new Candidate()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new Job()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = candidateService.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}
