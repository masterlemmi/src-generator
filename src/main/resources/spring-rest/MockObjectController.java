package com.taeza.tools.common.util.srcmaker;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MockObjectController {
    private final MockObjectService mockObjectService;

    @GetMapping("/api/mockObject")
    public List<MockObjectDTO> findAll() {
        return mockObjectService.findAll();
    }

    @GetMapping("/api/mockObject/{id}")
    public MockObjectDTO findById(@PathParam("id") @NonNull Long id) {
        return mockObjectService.findById(id);
    }

    @PostMapping("/api/mockObject")
    public MockObjectDTO save(@RequestBody MockObjectDTO mockObjectDTO) {
        return mockObjectService.save(mockObjectDTO);
    }

    @PutMapping("/api/mockObject")
    public MockObjectDTO update(@PathParam("id") @NonNull Long id, @RequestBody MockObjectDTO mockObjectDTO) {
        if (!id.equals(mockObjectDTO.getId())) {
            throw new IllegalArgumentException("Path id and request body id do not match");
        }

        return mockObjectService.update(mockObjectDTO);
    }
}
