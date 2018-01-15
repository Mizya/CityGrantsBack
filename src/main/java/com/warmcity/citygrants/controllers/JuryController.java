package com.warmcity.citygrants.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.warmcity.citygrants.dto.ProjectApplJuryDTO;
import com.warmcity.citygrants.gridFSDAO.GridFsDAO;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jury")
public class JuryController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private GridFsDAO gridFsService;

    @GetMapping("/project/{juryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectApplJuryDTO> getAllProjects(@PathVariable String juryId) {
        return projectService.getAllJuryProjects(juryId);
    }

    @PostMapping("/project/{projectId}/evaluation")
    @ResponseStatus(HttpStatus.OK)
    public void updateEvaluationOfProject(@PathVariable String projectId, @RequestBody Evaluation evaluation) {
        projectService.updateEvaluation(projectId, evaluation);
    }

    @PostMapping("/project/{projectId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void saveComment(@PathVariable String projectId, @RequestBody Comment comment) {
        projectService.saveComment(projectId, comment);
    }

    @GetMapping("/project/file/{fileId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getOneFileById(@PathVariable String fileId){
        GridFSDBFile imageFile = gridFsService.findOneById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageFile.getContentType()))
                .body(new InputStreamResource(imageFile.getInputStream()));
    }
}