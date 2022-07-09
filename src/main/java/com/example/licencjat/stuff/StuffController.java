package com.example.licencjat.stuff;

import com.example.licencjat.UI.Annotations.PreAuthorizeAdmin;
import com.example.licencjat.UI.Annotations.PreAuthorizeAdminAndClient;
import com.example.licencjat.stuff.models.*;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stuffs")
public class StuffController {
    private final StuffService stuffService;

    @PostMapping
    @PreAuthorizeAdmin
    public ResponseEntity<String> addStuff(@Valid @RequestBody StuffWebInput webInput) {
        return new ResponseEntity<>(
                stuffService.addStuff(StuffServiceCommand.builder().webInput(webInput).build()).toString(),
                HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorizeAdminAndClient
    public List<StuffListDto> getStuff() {
        return stuffService.getStuffs();
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
    @PreAuthorizeAdmin
    public void updateAStuff(@PathVariable("id") UUID id, @Valid @RequestBody StuffUpdateCommand updateCommand) {
        stuffService.editStuff(StuffServiceCommand.builder()
                .updateCommand(updateCommand)
                .id(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    @PreAuthorizeAdmin
    public void deleteStuff(@PathVariable("id") UUID id) {
        stuffService.deleteStuff(id);
    }
}
