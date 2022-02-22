package com.example.licencjat.stuff;

import com.example.licencjat.stuff.models.*;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stuffs/")
public class StuffController {
    private final StuffService stuffService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Resource created successfully")
    public void addStuff(@Valid @RequestBody StuffWebInput webInput) {
        stuffService.addStuff(StuffServiceCommand.builder()
                .webInput(webInput).build());
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<StuffListDto> getStuffs() {
        return stuffService.getStuffs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StuffDto getStuffById(@PathVariable("id") UUID id) {
        return stuffService.getStuffById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
    public void updateAStuff(@PathVariable("id") UUID id, @Valid @RequestBody StuffUpdateCommand updateCommand) {
        stuffService.editStuff(StuffServiceCommand.builder()
                .updateCommand(updateCommand)
                .id(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteStuff(@PathVariable("id") UUID id) {
        stuffService.deleteStuff(id);
    }
}
