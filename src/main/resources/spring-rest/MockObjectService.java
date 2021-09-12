package com.taeza.tools.common.util.srcmaker;

import com.taeza.tools.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MockObjectService {

    private final MockObjectRepository mockObjectRepository;
    private final MockObjectMapper mockObjectMapper;

    public List<MockObjectDTO> findAll() {
        return mockObjectRepository.findAll().stream().map(mockObjectMapper::toDTO).collect(toList());
    }

    public MockObjectDTO findById(Long id) {
        return mockObjectRepository.findById(id).map(mockObjectMapper::toDTO).orElseThrow(ResourceNotFoundException::new);
    }

    public MockObjectDTO save(MockObjectDTO mockObjectDTO) {
        MockObject saved = mockObjectRepository.save(mockObjectMapper.toEntity(mockObjectDTO));
        mockObjectDTO.setId(saved.getId());
        return mockObjectDTO;
    }

    public MockObjectDTO update(MockObjectDTO mockObjectDTO) {
        MockObject saved = mockObjectRepository.save(mockObjectMapper.toEntity(mockObjectDTO));
        return mockObjectMapper.toDTO(saved);
    }

    public void delete(Long id) {
        mockObjectRepository.deleteById(id);
    }

}
