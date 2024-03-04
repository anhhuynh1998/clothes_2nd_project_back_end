package com.example.clothes_2nd.service.home.statusHome;

import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.repository.StatusRepository;
import com.example.clothes_2nd.service.home.statusHome.response.StatusHomeResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StatusHomeService {
    private final StatusRepository statusRepository;
    public List<StatusHomeResponse> findAll(){
        List<StatusHomeResponse> result = new ArrayList<>();
        for(var item : statusRepository.findAll()){
            if(!item.getId().equals(1L)){
                StatusHomeResponse status = AppUtil.mapper.map(item, StatusHomeResponse.class);
                result.add(status);
            }
        }
        return result;
    }


}
