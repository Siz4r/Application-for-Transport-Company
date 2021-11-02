package com.example.licencjat.stuff;

import com.example.licencjat.stuff.models.StuffDto;
import com.example.licencjat.stuff.models.StuffListDto;

import com.example.licencjat.stuff.models.StuffServiceCommand;
import com.example.licencjat.stuff.models.StuffWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public StuffDto getStuffById(@PathVariable("id") String id) {
        return stuffService.getStuffById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource updated successfully")
    public void updateAStuff(@PathVariable("id") String id, @Valid @RequestBody StuffWebInput webInput) {
        stuffService.editStuff(StuffServiceCommand.builder()
                .webInput(webInput)
                .id(id).build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Resource deleted successfully")
    public void deleteStuff(@PathVariable("id") String id) {
        stuffService.deleteStuff(id);
    }
}
